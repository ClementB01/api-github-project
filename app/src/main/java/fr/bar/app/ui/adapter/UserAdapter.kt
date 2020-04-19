package fr.bar.app.ui.adapter

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.bar.app.data.model.GitHubUser
import fr.bar.app.ui.onClickItem
import fr.bar.app.ui.utils.dp
import fr.bar.app.ui.widget.holder.UserViewHolder
//import fr.bar.app.ui.widget.holder.OnUserClickListener

class UserAdapter(
    //private val onUserClickListener: OnUserClickListener
    private val listener: onClickItem
) : PagedListAdapter<GitHubUser, UserViewHolder>(Companion) {
//) : ListAdapter<GitHubUser, UserViewHolder>(Companion) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this, listener/*onUserClickListener*/) }
    }

    companion object : DiffUtil.ItemCallback<GitHubUser>() {
        override fun areItemsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem == newItem
        }
    }

    class OffsetDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            parent.run {
                outRect.set(
                    dp(16),
                    dp(0),
                    dp(16),
                    dp(8)
                )
            }
        }
    }
}