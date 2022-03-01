package dev.titto.userlistapp.data.remote

import dev.titto.userlistapp.data.remote.dto.UserListDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApi {
    @Headers("app-id: 618974f89894e5b6a24a441b")
    @GET("data/v1/user")
    fun getUserList(): Single<UserListDto>
}
