package com.domain.usecase

import com.domain.entities.UserPage
import com.domain.repository.UserRepository
import java.io.File

data class UserQueryParams(
    val page: Int,
)

interface Downloadable {
    fun cancel()
    fun pause()
    fun resume()
}

class UserUseCase(private val userRepository: UserRepository) {
    fun getUserList(queryParams: UserQueryParams, completion: (UserPage) -> Unit) {
        userRepository.getUserList(queryParams, completion)
    }

    fun uploadImage(file: File) {
        userRepository.uploadImage(file)
    }

    fun download(
        url: String, pathDir: String, fileName: String,
        onProgress: ((percentage: Float) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onError: (() -> Unit)? = null
    ): Downloadable {
       return userRepository.download(url, pathDir, fileName, onProgress, onComplete, onError)
    }
}