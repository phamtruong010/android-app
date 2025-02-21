package com.infrastructure.network

import android.os.Build
import com.infrastructure.local_storage.MMKV_STORAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.atomic.AtomicReference

class HeaderInterceptor : Interceptor {
    private var jobRef = AtomicReference<Deferred<String?>>(null)
    private var interceptorScope = CoroutineScope(Dispatchers.IO)

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }

    private fun setHeader4Request(
        request: Request.Builder,
        overrideToken: String? = null
    ): Request.Builder {

        val requestBuild = request.build()

        arrayOf(
            Pair("Content-Type", "application/json"),
            Pair("Accept", "application/json"),
            Pair("OS", "Android-${Build.VERSION.SDK_INT}"),
            Pair("Version", "")
        ).forEach {
            if (requestBuild.header(it.first) == null) {
                request.header(it.first, it.second)
            }
        }

        if (overrideToken != null) {
            request.addHeader(AUTHORIZATION_HEADER, "Bearer $overrideToken")
        } else if (MMKV_STORAGE.appToken != null) {
            request.addHeader(AUTHORIZATION_HEADER, "Bearer ${MMKV_STORAGE.appToken}")
        }
        return request
    }

    private suspend fun getNewToken(): String? {
        val currentJob = jobRef.get()
        return if (currentJob != null) {
            currentJob.await()
        } else {
            val newJob = interceptorScope.async {
                Networking.shared.refreshToken!!.invoke()
            }
            if (jobRef.compareAndSet(null, newJob)) {
                try {
                    newJob.await()
                } finally {
                    jobRef.set(null)
                }
            } else {
                jobRef.get()!!.await()
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = setHeader4Request(originalRequest.newBuilder())
        val response = chain.proceed(request.build())

        if (response.code == HTTPStatusCode.UN_AUTHORIZED.rawValue &&
            originalRequest.header("retried") == null &&
            originalRequest.header(
                "refresh"
            ) == null && Networking.shared.refreshToken != null
        ) {
            return runBlocking(interceptorScope.coroutineContext) {
                val newToken = getNewToken()
                if (newToken != null) {
                    MMKV_STORAGE.appToken = newToken
                    val newRequest = setHeader4Request(chain.request().newBuilder(), newToken)
                    newRequest.header("retried", "true")
                    return@runBlocking chain.proceed(newRequest.build())
                } else {
                    return@runBlocking response
                }

            }
        }
        return response
    }
}
