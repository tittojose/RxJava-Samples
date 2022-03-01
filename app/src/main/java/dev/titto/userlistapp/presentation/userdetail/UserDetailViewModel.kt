package dev.titto.userlistapp.presentation.userdetail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.titto.userlistapp.common.Resource
import dev.titto.userlistapp.domain.model.UserDetail
import dev.titto.userlistapp.domain.usecase.GetUserDetailUsecase
import dev.titto.userlistapp.domain.usecase.GetUsersUsecase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
        val getUserDetailUsecase: GetUserDetailUsecase,
        val stateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableLiveData<UserDetailState>()
    val state: LiveData<UserDetailState> = _state

    init {
        getUserDetail()
    }

    private fun getUserDetail() {
        getUserDetailUsecase(stateHandle.get<String>("userId")!!).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserDetailState.SUCCESS(result.data!!)
                }
                is Resource.Error -> {
                    _state.value = UserDetailState.ERROR(result.message ?: "Unexpected Error")
                }
                is Resource.Loading -> {
                    _state.value = UserDetailState.LOADING
                }
            }
        }.launchIn(viewModelScope)
    }
}