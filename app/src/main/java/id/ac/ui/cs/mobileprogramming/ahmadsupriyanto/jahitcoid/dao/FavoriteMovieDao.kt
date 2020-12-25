package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao

import androidx.room.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.FavoriteMovieDb
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM movie_table")
    fun getAll(): Flow<MutableList<FavoriteMovieDb>>

    @Query("SELECT * FROM movie_table WHERE title LIKE :title LIMIT 1")
    fun findByName(title: String): FavoriteMovieDb

    @Insert
    suspend fun insertAll(vararg projects: FavoriteMovieDb)

    @Delete
    suspend fun delete(project: FavoriteMovieDb)

}