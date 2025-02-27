package com.helloworld.adapter.repositories

import android.util.Log
import com.helloworld.adapter.api.IUserApi
import com.helloworld.adapter.networking.data_mapping.toDomain
import com.helloworld.adapter.networking.interfaces.NetworkingService
import com.helloworld.adapter.networking.interfaces.ResultWrapper
import com.helloworld.domain.entities.UserPage
import com.helloworld.domain.repository.UserRepository
import com.helloworld.domain.usecase.UserQueryParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

data class TokenResponse(val status: Int, val data: String)

class UserRepositoriesImpl(private val networkService: NetworkingService) : UserRepository {
    private var getUserJob: Job? = null

    override fun getUserList(queryParams: UserQueryParams, completion: (UserPage) -> Unit) {
        getUserJob?.cancel()
        getUserJob = CoroutineScope(Dispatchers.IO).launch {
            val service = networkService.service(IUserApi::class.java)
            val res = IntArray(5).toList().map {
                async {
                    val res = networkService.safeApiCall {
                        service.getUsers()
                    }
                    when (res) {
                        is ResultWrapper.Success -> {
                            Log.d("getUserList_success", res.data.results.size.toString())
                        }

                        is ResultWrapper.Failure -> {
                            Log.d("getUserList_failed_1", res.code.toString())
                        }

                        else -> {
                            Log.d("getUserList_failed", "Network error")
                        }
                    }
                    res
                }
            }.awaitAll()
            val listResponse = res.filter {
                when (it) {
                    is ResultWrapper.Success -> true
                    else -> false
                }
            }.map { (it as ResultWrapper.Success).data }
            if (listResponse.isNotEmpty()) {
                completion(listResponse.first().toDomain())
            }
        }

    }

    override fun uploadImage(file: File) {
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "sampleFile",
                file.name,
                RequestBody.create("image/*".toMediaTypeOrNull(), file)
            )
            .build()
        val service = networkService.service(IUserApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            networkService.safeApiCall {
                service.uploadImage(requestBody)
            }
            file.delete()
        }
    }

    override fun download(
        url: String,
        pathDir: String,
        fileName: String,
        onProgress: ((percentage: Float) -> Unit)?,
        onComplete: (() -> Unit)?,
        onError: (() -> Unit)?
    ):  com.helloworld.domain.usecase.Downloadable {
        val network = networkService.download(url, pathDir, fileName, onProgress, onComplete, onError)
        return  object : com.helloworld.domain.usecase.Downloadable {
            override fun cancel() {
                network.cancel()
            }

            override fun pause() {
                network.pause()
            }

            override fun resume() {
                network.resume()
            }
        }
    }
}