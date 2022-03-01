package dev.titto.userlistapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserListDto(
    @SerializedName("data")
    val userList: List<UserDto>,
    val limit: Int,
    val page: Int,
    val total: Int
)
