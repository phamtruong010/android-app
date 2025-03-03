package com.helloworld.adapter.networking.interfaces

interface Downloadable {
    fun cancel()
    fun pause()
    fun resume()
}

interface NetworkingService {
    fun <T> service(service: Class<T>): T
    fun <T> service(service: Class<T>, overrideUrl: String): T
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T>
    fun download(
        url: String, pathDir: String, fileName: String,
        onProgress: ((percentage: Float) -> Unit)?,
        onComplete: (() -> Unit)?,
        onError: (() -> Unit)?
    ): Downloadable
}