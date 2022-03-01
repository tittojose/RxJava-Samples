package dev.titto.userlistapp.presentation.mvp.userlist

import dev.titto.userlistapp.data.remote.dto.UserDto

interface UserListView {
    fun showUserList(userList: List<UserDto>)
    fun showErrorMessage(errorMessage: String)
}
