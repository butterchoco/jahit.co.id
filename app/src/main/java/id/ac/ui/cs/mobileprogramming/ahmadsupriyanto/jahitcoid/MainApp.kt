package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.app.Application
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.AppDatabase
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.MovieRepo
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.FavoriteMovieRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Created by wisnu on 8/7/18
 */
class MainApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val movieRepository by lazy { MovieRepo() }
    val favoriteMovieRepository by lazy { FavoriteMovieRepo(database.favoriteMovieDao()) }
}