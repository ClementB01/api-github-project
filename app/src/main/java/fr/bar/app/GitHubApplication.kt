package fr.bar.app

import android.app.Application
import fr.bar.app.data.database.DatabaseManager

/**
 * New entry point of the application (Referenced in the manifests)
 */
class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    // Init the database access
    private fun initDatabase() {
        DatabaseManager.getInstance(this)
    }
}