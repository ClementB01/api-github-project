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
                this.title = it.name
                this.setDisplayHomeAsUpEnabled(true)
            }
            view.apply {
                this.user_details_login.text = it.login
                this.user_details_location.text = it.location
                Glide.with(this)
                    .load(it.avatar_url)
                    .into(this.user_details_image_view)
            }
            view.user_details_url.text = it.url
            //loadEpisodes(it)
        }
    }

    companion object {
        const val ARG_USER_NAME_KEY = "arg_user_name_key"
    }
}