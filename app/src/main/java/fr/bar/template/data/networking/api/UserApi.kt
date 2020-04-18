package fr.bar.template.data.networking.api


import fr.bar.template.data.model.GitHubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Definition of end points for Character Api
 */
interface UserApi {

    @GET(GET_FIRST_PAGE_GITHUB_USER_PATH)
    suspend fun getFirstPageUsers(): Response<List<GitHubUser>>

    @GET(GET_USER_DETAILS)
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<GitHubUser>


    companion object {
        const val GET_FIRST_PAGE_GITHUB_USER_PATH = "users"
        const val GET_USER_DETAILS = "users/{username}"
    }

}