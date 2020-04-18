package fr.bar.template.data.networking.api


import fr.bar.template.data.model.GitHubUser
import retrofit2.Response
import retrofit2.http.GET

/**
 * Definition of end points for Character Api
 */
interface UserApi {

    @GET(GET_FIRST_PAGE_GITHUB_USER_PATH)
    suspend fun getFirstPageUsers(): Response<List<GitHubUser>>


    companion object {
        const val GET_FIRST_PAGE_GITHUB_USER_PATH = "users"
    }

}