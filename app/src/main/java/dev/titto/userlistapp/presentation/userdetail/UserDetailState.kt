package dev.titto.userlistapp.presentation.userdetail

import dev.titto.userlistapp.domain.model.UserDetail

sealed class UserDetailState {
    object LOADING : UserDetailState()
    data class SUCCESS(val userDetail: UserDetail) : UserDetailState()
    data class ERROR(val errorMessage: String) : UserDetailState()
}
