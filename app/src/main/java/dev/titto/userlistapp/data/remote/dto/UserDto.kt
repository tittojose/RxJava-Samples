package dev.titto.userlistapp.data.remote.dto

import dev.titto.userlistapp.domain.model.User

data class UserDto(
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
)

fun UserDto.toUser(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        picture = picture
    )
}

