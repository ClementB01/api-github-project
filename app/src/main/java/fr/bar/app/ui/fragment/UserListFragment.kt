package fr.bar.app.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import fr.bar.app.R
import fr.bar.app.data.model.GitHubUser
import fr.bar.app.ui.activity.MainActivity
import fr.bar.app.ui.adapter.UserAdapter
import fr.bar.app.ui.onClickItem
import fr.bar.app.ui.viewmodel.UserViewModel
//import fr.bar.app.ui.widget.holder.OnUserClickListener
import kotlinx.android.synthetic.main.fragment_user_list.view.*
import java.lang.IllegalStateException

class UserListFragment: Fragment(), onClickItem/*OnUserClickListener*/ {

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
            this.setTitle(R.string.profils)
            this.setDisplayHomeAsUpEnabled(false)
        }
        // We need to inject the OnUserClickListener in the constructor of the adapter
        userAdapter = UserAdapter(this)
        view.user_list_recycler_view.apply {
            adapter = userAdapter
            if (itemDecorationCount == 0) addItemDecoration(UserAdapter.OffsetDecoration())
        }

        /*userViewModel.getAllUsers {
            userAdapter.submitList(it)
        }*/

        userViewModel.usersPagedList.observe(this) {
            userAdapter.submitList(it)
        }
    }

    override fun onShortClick(gitHubUser: GitHubUser) {
        super.onShortClick(gitHubUser)
        findNavController().navigate(
            R.id.action_user_list_fragment_to_user_details_fragment,
            bundleOf(
                UserDetailsFragment.ARG_USER_NAME_KEY to gitHubUser.login
            )
        )
    }

    override fun onLongClick(gitHubUser: GitHubUser) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Would you add this user in database ?")
        builder.setCancelable(true)

        builder
            .setPositiveButton("Add") {
                _, _ -> kotlin.run {
                    userViewModel.insertUserDB(gitHubUser)
                    Toast.makeText(this.context, "User inserted", Toast.LENGTH_SHORT).show()
                }
            }

            .setNegativeButton("Cancel") {
                dialog, _ -> dialog.cancel()
            }

        val alert = builder.create()
        alert.show()

        val bp = alert.getButton(DialogInterface.BUTTON_POSITIVE)
        val paramsBp = bp.layoutParams as ViewGroup.MarginLayoutParams
        paramsBp.topMargin = 16 //Enter your desired margin value here.
        paramsBp.rightMargin = 16
        bp.layoutParams = paramsBp

        val bn = alert.getButton(DialogInterface.BUTTON_NEGATIVE)
        val paramsBn = bn.layoutParams as ViewGroup.MarginLayoutParams
        paramsBn.topMargin = 16 //Enter your desired margin value here.
        paramsBn.rightMargin = 16
        bn.layoutParams = paramsBn
    }

    // Implementation of OnUserClickListener
    /*override fun invoke(view: View, gitHubUser: GitHubUser) {
        findNavController().navigate(
            R.id.action_user_list_fragment_to_user_details_fragment,
            bundleOf(
                UserDetailsFragment.ARG_USER_NAME_KEY to gitHubUser.login
            )
        )
    }*/
}