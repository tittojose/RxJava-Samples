package dev.titto.userlistapp.presentation.userlist

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.titto.userlistapp.R
import dev.titto.userlistapp.databinding.FragmentUserListBinding

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UserListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserListBinding.bind(view)
        viewModel.state.observe(viewLifecycleOwner) { it ->
            when (it) {
                is UserListState.LOADING -> {
                    binding.progressUserListFetch.visibility = VISIBLE
                }
                is UserListState.EMPTY -> {
                    showToast("EMPTY")
                }
                is UserListState.ERROR -> {
                    showToast("ERROR")
                }
                is UserListState.SUCCESS -> {
                    binding.progressUserListFetch.visibility = GONE
                    binding.recyclerviewUserlist.visibility = VISIBLE
                    binding.recyclerviewUserlist.adapter = UserListRecyclerAdapter(it.userList) {
                        val action =
                            UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(it.id)
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(value: String) {
        Toast.makeText(requireActivity(), value, Toast.LENGTH_SHORT).show()
    }

}