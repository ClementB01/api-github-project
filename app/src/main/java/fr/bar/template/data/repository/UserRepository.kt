package fr.bar.template.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import fr.bar.template.data.networking.datasource.UserDataSource
import fr.bar.template.data.model.GitHubUser
import fr.bar.template.data.networking.HttpClientManager
import fr.bar.template.data.networking.api.UserApi
import fr.bar.template.data.networking.createApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class UserRepositoryImpl(
    private  val api: UserApi
): UserRepository {

    private val paginationConfig = PagedList.Config
        .Builder()
        // If you set true you will have to catch
        // the place holder case in the adapter
        .setEnablePlaceholders(false)
        .setPageSize(30)
        .build()

    override fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<GitHubUser>> {
        return LivePagedListBuilder(
            UserDataSource.Factory(api, scope),
            paginationConfig
        ).build()
    }

    override suspend fun getListUser(): List<GitHubUser>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getFirstPageUsers()
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}" }
                response.body() ?: throw IllegalStateException("Body is null")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun getUserDetails(username: String): GitHubUser? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getUserDetails(username)
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}" }
                response.body() ?: throw IllegalStateException("Body is null")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}

interface UserRepository {

    fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<GitHubUser>>

    suspend fun getListUser(): List<GitHubUser>?

    suspend fun getUserDetails(username: String): GitHubUser?

    companion object {
        val instance: UserRepository by lazy {
            UserRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}