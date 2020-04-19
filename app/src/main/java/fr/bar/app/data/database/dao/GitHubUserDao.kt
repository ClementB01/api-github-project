package fr.bar.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.bar.app.data.model.GitHubUser

@Dao
interface GitHubUserDao {

    @Query("SELECT * FROM githubuser")
    fun selectAll(): List<GitHubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGitHubUser(vararg githubuser: GitHubUser)

}