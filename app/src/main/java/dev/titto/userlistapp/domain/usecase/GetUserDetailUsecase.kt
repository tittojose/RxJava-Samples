package dev.titto.userlistapp.domain.usecase

import dev.titto.userlistapp.common.Resource
import dev.titto.userlistapp.data.remote.dto.toUserDetail
import dev.titto.userlistapp.domain.model.UserDetail
import dev.titto.userlistapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserDetailUsecase @Inject constructor(
        private val repository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<UserDetail>> = flow {
        try {
            emit(Resource.Loading())
            val userDetail = repository.getUserDetail(userId = userId).toUserDetail()
            emit(Resource.Success(userDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}

