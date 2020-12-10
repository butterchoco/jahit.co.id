package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.app.Application
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.AppDatabase
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.AppDatabase.Companion.getDatabase
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.MovieRepo
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.ProjectRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.applicationContext

/**
 * Created by wisnu on 8/7/18
 */
class MainApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val projectRepository by lazy { ProjectRepo(database.projectDao()) }
    val movieRepository by lazy { MovieRepo() }
}