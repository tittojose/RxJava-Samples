package dev.titto.userlistapp.presentation.mvp.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.titto.userlistapp.data.remote.dto.UserDto
import dev.titto.userlistapp.databinding.ItemUserListBinding

class UserListRecyclerAdapter(
        val userList: List<UserDto>
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
                ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = UserViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = userList.size
}

class UserViewHolder(private val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    fun bind(user: UserDto) {
        binding.apply {
            textviewUserName.text = "${user.firstName} ${user.lastName}"
            Glide.with(itemView)
                    .load(user.picture)
                    .into(imageviewUserPic)
        }
    }
}
