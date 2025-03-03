package com.helloworld.infrastructure.network

import com.helloworld.adapter.networking.interfaces.Downloadable
import com.helloworld.adapter.networking.interfaces.NetworkingService
import com.helloworld.adapter.networking.interfaces.ResultWrapper
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.helloworld.infrastructure.local_storage.MMKV_STORAGE
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

class NetworkingServiceImpl(
    private val retrofit: Retrofit,
    private val getRetrofit: (url: String) -> Retrofit
) : NetworkingService {

    override fun <T> service(service: Class<T>): T {
        return retrofit.create(service)
    }

    override fun <T> service(service: Class<T>, overrideUrl: String): T {
        return getRetrofit(overrideUrl).create(service)
    }

    override suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
        return try {
            val res = apiCall()
            return ResultWrapper.Success(res)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    ResultWrapper.Failure(throwable.code(), throwable.message())
                }

                else -> {
                    ResultWrapper.Failure(null, null)
                }
            }
        }
    }

    override fun download(
        url: String, pathDir: String, fileName: String, onProgress: ((percentage: Float) -> Unit)?,
        onComplete: (() -> Unit)?,
        onError: (() -> Unit)?
    ): Downloadable {
        val downloadId = PRDownloader.download(url, pathDir, fileName)
            .setHeader(HeaderInterceptor.AUTHORIZATION_HEADER, "Bearer ${MMKV_STORAGE.appToken}")
            .build()
            .setOnProgressListener { progress ->
                progress?.let {
                    onProgress?.invoke(it.currentBytes.toFloat() / it.totalBytes.toFloat())
                }
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    onComplete?.invoke()
                }

                override fun onError(error: Error?) {
                    onError?.invoke()
                }

            })
        val downloadable = object : Downloadable {
            override fun cancel() {
                PRDownloader.cancel(downloadId)
            }

            override fun pause() {
                PRDownloader.pause(downloadId)
            }

            override fun resume() {
                PRDownloader.resume(downloadId)
            }
        }

        return downloadable
    }
}