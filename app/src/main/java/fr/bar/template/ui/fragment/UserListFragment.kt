package fr.bar.template.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fr.bar.template.R
import fr.bar.template.ui.activity.MainActivity
import fr.bar.template.ui.adapter.UserAdapter
import fr.bar.template.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_list.view.*
import java.lang.IllegalStateException

class UserListFragment: Fragment() {

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

        userAdapter = UserAdapter()
        view.user_list_recycler_view.apply {
            adapter = userAdapter
        }
        userViewModel.getAllUsers {
            userAdapter.submitList(it)
        }
    }
}