package fr.bar.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.bar.app.data.database.dao.GitHubUserDao
import fr.bar.app.data.model.GitHubUser


@Database(
    entities = [
        GitHubUser::class
    ],
    version = 1,
    exportSchema = true
)
abstract class GitHubDatabase : RoomDatabase() {
    abstract val githubuserDao: GitHubUserDao
}