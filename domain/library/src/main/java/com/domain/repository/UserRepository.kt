package com.domain.repository

import com.domain.entities.UserPage
import com.domain.usecase.Downloadable
import com.domain.usecase.UserQueryParams
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