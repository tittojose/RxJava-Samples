package dev.titto.userlistapp.presentation.mvp.userlist

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import dev.titto.userlistapp.data.remote.dto.UserDto
import dev.titto.userlistapp.data.remote.dto.UserListDto
import dev.titto.userlistapp.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.io.IOException

class UserListPresenterTest {

    lateinit var presenter: UserListPresenter
    var view: UserListView = mock()
    var repo: UserRepository = mock()

    @Before
    fun setUp() {
        presenter = UserListPresenter(
            view = view,
            repo = repo,
            schedulers = TestScheduleProvider()
        )
    }

    @Test
    fun `when api return response display userlist`() {
        val userList = listOf(
            UserDto(
                firstName = "Test",
                id = "123",
                lastName = "Last",
                picture = "sample",
                title = "SoftEngg"
            )
        )
        whenever(repo.getUserList()).thenReturn(
            Single.just(
                UserListDto(
                    userList = userList,
                    limit = 10,
                    page = 0,
                    total = 1
                )
            )
        )

        presenter.fetchUsers()

        verify(view).showUserList(userList)
    }


    @Test
    fun `when api return error display userlist`() {

        whenever(repo.getUserList()).thenReturn(Single.error(IOException()))

        presenter.fetchUsers()

        verify(view).showErrorMessage("Error loading data")
    }
}