package com.helloworld.adapter.networking.data_mapping


import com.helloworld.domain.entities.UserEntity
import com.helloworld.domain.entities.UserPage
import com.google.gson.annotations.SerializedName

data class UserResponseDTO(
    @SerializedName("info")
    val infos: Info,
    val results: List<UserDTO>) {

    data class Info(val page: Int, val results: Int, val seed: String, val version: String)

    data class UserDTO(
        val phone: String,
        val nat: String,
        val gender: String,
        val name: Name,
        val email: String
    ) {
        data class Name(val title: String, val first: String, val last: String)
    }
}

fun UserResponseDTO.toDomain(): UserPage {
    return UserPage(page = infos.page,
        totalPage = infos.results,
        users = results.map {
            it.toDomain()
        })
}

fun UserResponseDTO.UserDTO.toDomain(): UserEntity {
    return UserEntity(
        id = nat,
        name = name.first,
        email = email,
        gender = gender.string2Gender()
    )
}

private fun String.string2Gender(): UserEntity.Gender {
    return when (this) {
        "male" -> UserEntity.Gender.male
        else -> UserEntity.Gender.female
    }
}