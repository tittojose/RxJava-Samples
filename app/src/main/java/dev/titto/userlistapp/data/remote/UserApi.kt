package dev.titto.userlistapp.data.remote

import dev.titto.userlistapp.data.remote.dto.UserDetailDto
import dev.titto.userlistapp.data.remote.dto.UserListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserApi {
    @Headers("app-id: 618974f89894e5b6a24a441b")
    @GET("data/v1/user")
    suspend fun getUserList(): UserListDto

    @Headers("app-id: 618974f89894e5b6a24a441b")
    @GET("data/v1/user/{userId}")
    suspend fun getUserDetail(@Path("userId") userId: String): UserDetailDto
}
