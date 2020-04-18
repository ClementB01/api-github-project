package fr.bar.template.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import fr.bar.template.data.model.GitHubUser
import fr.bar.template.ui.widget.holder.UserViewHolder
import fr.bar.template.ui.widget.holder.OnUserClickListener

class UserAdapter(
    private val onUserClickListener: OnUserClickListener
) : ListAdapter<GitHubUser, UserViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this, onUserClickListener) }
    }

    companion object : DiffUtil.ItemCallback<GitHubUser>() {
        override fun areItemsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem == newItem
        }
    }
}