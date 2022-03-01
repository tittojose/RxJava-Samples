package dev.titto.userlistapp.presentation.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.titto.userlistapp.common.Resource
import dev.titto.userlistapp.domain.usecase.GetUsersUsecase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    val usersUsecase: GetUsersUsecase
) : ViewModel() {
    private val _state = MutableLiveData<UserListState>()
    val state: LiveData<UserListState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        usersUsecase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data.isNullOrEmpty()) {
                        _state.value = UserListState.EMPTY
                    } else {
                        _state.value = UserListState.SUCCESS(result.data)
                    }
                }
                is Resource.Error -> {
                    _state.value = UserListState.ERROR(result.message ?: "Unexpected Error")
                }
                is Resource.Loading -> {
                    _state.value = UserListState.LOADING
                }
            }
        }.launchIn(viewModelScope)
    }
}