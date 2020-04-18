package fr.bar.template.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.bar.template.data.model.GitHubUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

class UserRepositoryTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()
    private val testDispatcher = newSingleThreadContext("UI context")
    private lateinit var repository: UserRepository

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
        Dispatchers.setMain(testDispatcher)
        repository = UserRepository.instance
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.close()
    }

    @Test
    fun getCharacterDetails() = runBlocking {
        val value = repository.getUserDetails("octocat")
        Assert.assertEquals("Should be octocat", octocat, value)
    }
}