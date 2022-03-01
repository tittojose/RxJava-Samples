package dev.titto.userlistapp.domain.repository

import dev.titto.userlistapp.data.remote.dto.UserListDto
import io.reactivex.Single

interface UserRepository {
    fun getUserList(): Single<UserListDto>
}
