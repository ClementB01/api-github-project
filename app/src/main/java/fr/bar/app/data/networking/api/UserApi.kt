package fr.bar.app.data.networking.api


import fr.bar.app.data.model.GitHubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Definition of end points for Character Api
 */
interface UserApi {

    @GET(GET_GITHUB_USERS_PATH)
    suspend fun getFirstPageUsers(): Response<List<GitHubUser>>

    @GET(GET_USER_DETAILS)
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<GitHubUser>

    @GET(GET_GITHUB_USERS_PATH)
    suspend fun getAllUser(
        @Query("since") since: Int
    ): Response<List<GitHubUser>>

    companion object {
        const val GET_GITHUB_USERS_PATH = "users"
        const val GET_USER_DETAILS = "users/{username}"
    }

}