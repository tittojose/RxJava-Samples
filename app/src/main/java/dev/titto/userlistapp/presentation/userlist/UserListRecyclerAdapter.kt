package dev.titto.userlistapp.presentation.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.titto.userlistapp.databinding.ItemUserListBinding
import dev.titto.userlistapp.domain.model.User

class UserListRecyclerAdapter(
        val userList: List<User>,
        private val onItemClickListener: (User) -> Unit
) :
        RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
                ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = UserViewHolder(binding)
        viewHolder.itemView.setOnClickListener {
            onItemClickListener.invoke(userList[viewHolder.adapterPosition])
        }
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
    fun bind(user: User) {
        binding.apply {
            textviewUserName.text = "${user.firstName} ${user.lastName}"
            Glide.with(itemView)
                    .load(user.picture)
                    .into(imageviewUserPic)
        }
    }
}
