package fr.bar.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import fr.bar.app.R
import fr.bar.app.ui.activity.MainActivity
import fr.bar.app.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.fragment_user_details.view.*
import kotlinx.android.synthetic.main.holder_user.view.*
import java.lang.IllegalStateException

class UserDetailsFragment: Fragment() {

    private lateinit var userViewModel: UserViewModel
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            userViewModel = ViewModelProvider(this, UserViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
        userName =
            arguments?.getString(ARG_USER_NAME_KEY) ?: throw IllegalStateException("No NAME found")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUser(view)
    }


    private fun loadUser(view: View) {
        userViewModel.getUserDetails(userName) {
            (activity as? MainActivity)?.supportActionBar?.apply {
                this.setTitle(R.string.details)
                this.setDisplayHomeAsUpEnabled(true)
            }
            view.apply {
                this.user_details_login.text = it.login

                if(it.email != null){
                    this.user_details_mail.text = it.email
                } else {
                    this.user_details_mail.text = context.getString(R.string.no_mail)
                }

                this.user_details_created.text = it.created_at
                this.user_details_bio.text = it.bio
                this.user_details_location.text = it.location
                this.user_details_public_repo.text = it.public_repos.toString()
                this.user_details_followers.text = it.followers.toString()
                this.user_details_following.text = it.following.toString()
                this.user_details_blog.text = it.blog

                Glide.with(this)
                    .load(it.avatar_url)
                    .into(this.user_detail_avatar)
            }
        }
    }

    companion object {
        const val ARG_USER_NAME_KEY = "arg_user_name_key"
    }
}