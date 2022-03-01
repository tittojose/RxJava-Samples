package dev.titto.userlistapp.domain.repository

import dev.titto.userlistapp.data.remote.dto.UserDetailDto
import dev.titto.userlistapp.data.remote.dto.UserListDto
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserList(): UserListDto
    suspend fun getUserDetail(userId: String): UserDetailDto
}
