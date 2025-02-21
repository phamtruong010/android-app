package com.infrastructure.network

enum class HTTPStatusCode {
    OK,
    UN_AUTHORIZED,
    UNKNOWN,
    DECODE_ERROR,
    CANCELED;

    val rawValue: Int
        get() {
            return when (this) {
                OK -> 200
                UN_AUTHORIZED -> 401
                UNKNOWN -> -100
                DECODE_ERROR -> -101
                CANCELED -> 999
            }
        }
}