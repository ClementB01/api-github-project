package fr.bar.template.data.networking.api

import fr.bar.template.data.model.GitHubUser
import fr.bar.template.data.networking.HttpClientManager
import fr.bar.template.data.networking.createApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserApiTest {

    private lateinit var instance: HttpClientManager
    private lateinit var api: UserApi

    private val mojombo = GitHubUser(
        "mojombo",
        1,
        "MDQ6VXNlcjE=",
        "https://avatars0.githubusercontent.com/u/1?v=4",
        "",
        "https://api.github.com/users/mojombo",
        "https://github.com/mojombo",
        "https://api.github.com/users/mojombo/followers",
        "https://api.github.com/users/mojombo/following{/other_user}",
        "https://api.github.com/users/mojombo/gists{/gist_id}",
        "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
        "https://api.github.com/users/mojombo/subscriptions",
        "https://api.github.com/users/mojombo/orgs",
        "https://api.github.com/users/mojombo/repos",
        "https://api.github.com/users/mojombo/events{/privacy}",
        "https://api.github.com/users/mojombo/received_events",
        "User",
        false,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )

    private val octocat = GitHubUser (
        "octocat",
        583231,
        "MDQ6VXNlcjU4MzIzMQ==",
        "https://avatars3.githubusercontent.com/u/583231?v=4",
        "",
        "https://api.github.com/users/octocat",
        "https://github.com/octocat",
        "https://api.github.com/users/octocat/followers",
        "https://api.github.com/users/octocat/following{/other_user}",
        "https://api.github.com/users/octocat/gists{/gist_id}",
        "https://api.github.com/users/octocat/starred{/owner}{/repo}",
        "https://api.github.com/users/octocat/subscriptions",
        "https://api.github.com/users/octocat/orgs",
        "https://api.github.com/users/octocat/repos",
        "https://api.github.com/users/octocat/events{/privacy}",
        "https://api.github.com/users/octocat/received_events",
        "User",
        false,
        "The Octocat",
        "GitHub",
        "http://www.github.com/blog",
        "San Francisco",
        null,
        null,
        null,
        8,
        8,
        3004,
        9,
        "2011-01-25T18:44:36Z",
        "2020-03-23T14:29:23Z"
    )

    @Before
    fun setUp() {
        instance = HttpClientManager.instance
        api = instance.createApi()
    }

    @Test
    fun getPaginatedListTest() = runBlocking {
        // region Test initialisation
        val count = 30
        val firstUser = mojombo

        // endregion

        api.getAllUser(0).apply {
            Assert.assertTrue("Request must be a success", this.isSuccessful)
            val data: List<GitHubUser> =
                this.body() ?: throw IllegalStateException("Body is null")
            Assert.assertEquals(
                "Same pagination info", count, data.size
            )
            Assert.assertEquals(
                "First User must be Mojombo", firstUser, data.first()
            )
        }

        return@runBlocking
    }

    @Test
    fun getFirstPageUsersTest() = runBlocking {

        // region Test initialisation
        val count = 30
        val firstUser = mojombo

        // endregion

        api.getFirstPageUsers().apply {
            Assert.assertTrue("Request must be a success", this.isSuccessful)
            val data: List<GitHubUser> =
                this.body() ?: throw IllegalStateException("Body is null")
            Assert.assertEquals(
                "Same pagination info", count, data.size
            )
            Assert.assertEquals(
                "First User must be Mojombo", firstUser, data.first()
            )
        }

        return@runBlocking
    }

    @Test
    fun getUserDetailsTest() = runBlocking {
        api.getUserDetails("octocat").run {
            Assert.assertTrue("Request must be a success", this.isSuccessful)
            Assert.assertEquals("Should be octocat", octocat, this.body())
        }
    }
}