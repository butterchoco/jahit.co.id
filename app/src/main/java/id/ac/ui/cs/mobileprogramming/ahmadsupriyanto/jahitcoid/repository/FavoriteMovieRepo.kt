package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository

import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.FavoriteMovieDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.FavoriteMovieDb
import kotlinx.coroutines.flow.Flow
import java.util.*

class FavoriteMovieRepo(private val favoriteMovieDao: FavoriteMovieDao) {

    suspend fun saveProject(favoriteMovieDb: FavoriteMovieDb) {
        favoriteMovieDao.insertAll(favoriteMovieDb)
    }

    fun generateFavoriteMovie(
        id: Int,
        voteAverage: Double,
        overview: String,
        releaseDate: String,
        title: String,
        adult: Boolean,
        backdropPath: String,
        video: Boolean,
        originalLanguage: String,
        originalTitle: String,
        posterPath: String,
        popularity: Double,
        mediaType: String,
        voteCount: Int
    ): FavoriteMovieDb {
        return FavoriteMovieDb(
            id,
            voteAverage,
            overview,
            releaseDate,
            title,
            adult,
            backdropPath,
            video,
            originalLanguage,
            originalTitle,
            posterPath,
            popularity,
            mediaType,
            voteCount
        )
    }

    fun getAll(): Flow<MutableList<FavoriteMovieDb>> {
        return favoriteMovieDao.getAll()
    }

    private fun generateUuid(): String = UUID.randomUUID().toString()

    suspend fun deleteProject(projectDbFavorite: FavoriteMovieDb) {
        favoriteMovieDao.delete(projectDbFavorite)
    }

}