package com.helloworld.domain.entities

data class UserEntity(val id: String, val name: String, var gender: Gender, val email: String) {
    enum class Gender {
        male,
        female
    }
}

data class UserPage(val page: Int, val totalPage: Int, val users: List<UserEntity>)