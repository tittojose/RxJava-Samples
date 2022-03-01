package dev.titto.userlistapp.presentation.userlist

import dev.titto.userlistapp.domain.model.User

sealed class UserListState {
    object LOADING : UserListState()
    object EMPTY : UserListState()
    data class SUCCESS(val userList: List<User>) : UserListState()
    data class ERROR(val errorMessage: String) : UserListState()
}
