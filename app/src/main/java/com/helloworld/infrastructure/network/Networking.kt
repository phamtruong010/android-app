package com.helloworld.infrastructure.network

import com.helloworld.adapter.networking.interfaces.NetworkingService
import okhttp3.CertificatePinner
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Networking {
    private var retrofit: Retrofit? = null
    private var url: String = ""
    private val dispatcher = Dispatcher()

    var refreshToken: (() -> String?)? = null

    companion object {
        val shared: Networking = Networking()
        const val TIME_OUT = 1L
    }

    init {
        dispatcher.maxRequests = 100
        dispatcher.maxRequestsPerHost = 10
    }

    private fun getClient(certificatePinner: CertificatePinner? = null): OkHttpClient {
        val client = OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .callTimeout(TIME_OUT, TimeUnit.MINUTES)
            .connectTimeout(TIME_OUT, TimeUnit.MINUTES)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(HeaderInterceptor());
        certificatePinner?.let {
            client.certificatePinner(it)
        }

        return client.build()
    }

    private fun getRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(getClient())
            .build()
    }

    fun enableSSLPinning(configs: List<SSLPinningConfig>) {
        val certificatePinnerBuilder = CertificatePinner.Builder()

        for (element in configs) {
            val publicKeyHashesArray = element.publicKeyHashes.map {
                "sha256/$it"
            }
            certificatePinnerBuilder.add(
                "**." + element.domain,
                *(publicKeyHashesArray.toTypedArray())
            )
        }

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(getClient(certificatePinnerBuilder.build()))
            .build()
    }

    fun disableSSLPinning() {
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(getClient())
            .build()
    }

    fun cancelAllRequest() {
        dispatcher.cancelAll()
    }

    fun setupUrl(url: String) {
        this.url = url
        retrofit = getRetrofit(url)
    }

    fun getNetworkService(): NetworkingService {
        return NetworkingServiceImpl(retrofit!!, getRetrofit = { getRetrofit(it) })
    }
}

