package fr.bar.template.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import fr.bar.template.R
import fr.bar.template.data.model.GitHubUser
import fr.bar.template.ui.activity.MainActivity
import fr.bar.template.ui.adapter.UserAdapter
import fr.bar.template.ui.viewmodel.UserViewModel
import fr.bar.template.ui.widget.holder.OnUserClickListener
import kotlinx.android.synthetic.main.fragment_user_list.view.*
import java.lang.IllegalStateException

class UserListFragment: Fragment(), OnUserClickListener {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            userViewModel = ViewModelProvider(this, UserViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.supportActionBar?.apply {
            this.setTitle(R.string.app_name)
            this.setDisplayHomeAsUpEnabled(false)
        }
        // We need to inject the OnUserClickListener in the constructor of the adapter
        userAdapter = UserAdapter(this)
        view.user_list_recycler_view.apply {
            adapter = userAdapter
        }
        userViewModel.getAllUsers {
            userAdapter.submitList(it)
        }
    }

    // Implementation of OnUserClickListener
    override fun invoke(view: View, gitHubUser: GitHubUser) {
        findNavController().navigate(
            R.id.action_user_list_fragment_to_user_details_fragment,
            bundleOf(
                UserDetailsFragment.ARG_USER_NAME_KEY to gitHubUser.login
            )
        )
    }
}