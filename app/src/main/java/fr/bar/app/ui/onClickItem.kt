package fr.bar.app.ui

import fr.bar.app.data.model.GitHubUser

interface onClickItem {

    fun onShortClick(gitHubUser: GitHubUser) {

    }

    fun onLongClick(gitHubUser: GitHubUser) {

    }
}