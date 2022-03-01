package dev.titto.userlistapp.presentation.mvp.userlist

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.titto.userlistapp.data.remote.dto.UserDto
import dev.titto.userlistapp.data.repository.UserRepositoryImpl
import dev.titto.userlistapp.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity(), UserListView {

    private var _binding: ActivityUserListBinding? = null
    private val binding get() = _binding!!
    lateinit var presenter: UserListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repo = UserRepositoryImpl(APIHelper.getAPIService())

        presenter = UserListPresenter(view = this, repo)

        presenter.fetchUsers()
    }

    override fun showUserList(userList: List<UserDto>) {
        binding.progressUserListFetch.visibility = GONE
        binding.recyclerviewUserlist.visibility = VISIBLE
        binding.recyclerviewUserlist.adapter = UserListRecyclerAdapter(userList)
    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this@UserListActivity, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}