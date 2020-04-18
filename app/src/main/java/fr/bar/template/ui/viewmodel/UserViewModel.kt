package fr.bar.template.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.bar.template.data.model.GitHubUser
import fr.bar.template.data.repository.UserRepository
import kotlinx.coroutines.launch

open class UserViewModel(
    private  val repository: UserRepository
) :ViewModel() {

    /**
     *  Return the list of User from the API
     */
    fun getAllUsers(onSuccess: OnSuccess<List<GitHubUser>>){
        viewModelScope.launch {
            repository.getListUser()?.run(onSuccess)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(UserRepository.instance) as T
        }
    }
}