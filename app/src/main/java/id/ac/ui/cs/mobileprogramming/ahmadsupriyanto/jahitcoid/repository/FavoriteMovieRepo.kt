package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository

import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.FavoriteMovieDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.FavoriteMovieDb
import kotlinx.coroutines.flow.Flow
import java.util.*

class FavoriteMovieRepo(private val favoriteMovieDao: FavoriteMovieDao) {

    suspend fun saveProject(projectDbFavorite: FavoriteMovieDb) {
        favoriteMovieDao.insertAll(projectDbFavorite)
    }

    fun getAll(): Flow<MutableList<FavoriteMovieDb>> {
        return favoriteMovieDao.getAll()
    }

    private fun generateUuid(): String = UUID.randomUUID().toString()

    suspend fun deleteProject(projectDbFavorite: FavoriteMovieDb) {
        favoriteMovieDao.delete(projectDbFavorite)
    }

}