package dev.titto.userlistapp.data.remote.dto

import dev.titto.userlistapp.domain.model.UserDetail

data class UserDetailDto(
        val dateOfBirth: String,
        val email: String,
        val firstName: String,
        val gender: String,
        val id: String,
        val lastName: String,
        val location: Location,
        val phone: String,
        val picture: String,
        val registerDate: String,
        val title: String,
        val updatedDate: String
)


fun UserDetailDto.toUserDetail(): UserDetail =
        UserDetail(
                dateOfBirth = dateOfBirth,
                email = email,
                firstName = firstName,
                gender = gender,
                id = id,
                lastName = lastName,
                location = location.city + ", " + location.state + ", " + location.country,
                phone = phone,
                registerDate = registerDate,
                picture = picture,
                title = title,
                updatedDate = updatedDate
        )
