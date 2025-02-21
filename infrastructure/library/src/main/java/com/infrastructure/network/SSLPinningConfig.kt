package com.infrastructure.network

data class SSLPinningConfig(val domain: String, val publicKeyHashes: List<String>)
