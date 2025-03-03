package com.helloworld.domain.repository

import com.helloworld.domain.entities.UserPage
import com.helloworld.domain.usecase.Downloadable
import com.helloworld.domain.usecase.UserQueryParams
import java.io.File

interface UserRepository {
    fun getUserList(queryParams: UserQueryParams, completion: (UserPage) -> Unit)
    fun uploadImage(file: File)
    fun download(
        url: String, pathDir: String, fileName: String,
        onProgress: ((percentage: Float) -> Unit)?,
        onComplete: (() -> Unit)?,
        onError: (() -> Unit)?
    ): Downloadable
}