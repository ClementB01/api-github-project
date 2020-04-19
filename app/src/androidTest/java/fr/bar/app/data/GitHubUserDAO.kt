package fr.bar.app.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import fr.bar.app.data.database.GitHubDatabase
import fr.bar.app.data.database.dao.GitHubUserDao
import fr.bar.app.data.model.GitHubUser
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class UserDaoTest {

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


    private lateinit var dao: GitHubUserDao

    @Before
    fun setUp() {
        dao = database.githubuserDao
    }

    @Test
    fun selectAll() = runBlocking {
        dao.insertGitHubUser(octocat)
        assertEquals("Should be same", octocat, dao.selectAll().first())
    }

    companion object {
        private lateinit var database: GitHubDatabase

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().targetContext,
                GitHubDatabase::class.java
            ).build()
        }
    }
}