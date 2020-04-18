package fr.bar.template.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import fr.bar.template.R
import fr.bar.template.ui.activity.MainActivity
import fr.bar.template.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_details.view.*
import java.lang.IllegalStateException

class UserDetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        const val ARG_USER_NAME_KEY = "arg_user_name_key"
    }
}