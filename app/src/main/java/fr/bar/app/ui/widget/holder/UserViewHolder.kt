package fr.bar.app.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.bar.app.R
import fr.bar.app.data.model.GitHubUser
import fr.bar.app.ui.onClickItem
import kotlinx.android.synthetic.main.holder_user.view.*

/**
 * SAM (Single Abstract Method) to listen a click.
 *
 * This callback contains the view clicked, and the character attached to the view
 */
//typealias OnUserClickListener = (view: View, gitHubUser: GitHubUser) -> Unit

class UserViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: GitHubUser, listner: onClickItem/*OnUserClickListener*/) {
        itemView.apply {
            this.setOnClickListener { listner.onShortClick(model) }
            this.setOnLongClickListener {
                listner.onLongClick(model)
                return@setOnLongClickListener true
            }
            this.holder_user_login.text = model.login
            this.holder_user_type.text = "Compte de type : " + model.type
            Glide.with(this)
                .load(model.avatar_url)
                .into(this.holder_user_avatar)
        }
    }

    companion object {
        /**
         * Create a new Instance of [UserViewHolder]
         */
        fun create(parent: ViewGroup): UserViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_user,
                parent,
                false
            )
            return UserViewHolder(view)
        }
    }

}