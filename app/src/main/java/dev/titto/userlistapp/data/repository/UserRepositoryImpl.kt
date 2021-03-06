package dev.titto.userlistapp.data.repository

import dev.titto.userlistapp.data.remote.UserApi
import dev.titto.userlistapp.data.remote.dto.UserDetailDto
import dev.titto.userlistapp.data.remote.dto.UserListDto
import dev.titto.userlistapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl constructor(
        private val api: UserApi
) : UserRepository {
    override fun getUserList() = api.getUserList()
}
