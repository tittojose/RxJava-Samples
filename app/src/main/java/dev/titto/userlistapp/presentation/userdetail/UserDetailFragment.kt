package dev.titto.userlistapp.presentation.userdetail

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.titto.userlistapp.R
import dev.titto.userlistapp.databinding.FragmentUserDetailBinding

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UserDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserDetailBinding.bind(view)
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is UserDetailState.LOADING -> {
                    binding.progressUserListFetch.visibility = VISIBLE
                }
                is UserDetailState.ERROR -> {
                    showToast("ERROR")
                }
                is UserDetailState.SUCCESS -> {
                    binding.progressUserListFetch.visibility = GONE
                    binding.textviewUserName.text = it.userDetail.firstName
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