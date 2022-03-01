package dev.titto.userlistapp.domain.usecase

import dev.titto.userlistapp.common.Resource
import dev.titto.userlistapp.data.remote.dto.toUser
import dev.titto.userlistapp.domain.model.User
import dev.titto.userlistapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUsecase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading())
            val userList = repository.getUserList().userList.map { it.toUser() }
            emit(Resource.Success(userList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}