package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel

import android.util.Log
import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.FavoriteMovieDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.repository.FavoriteMovieRepo
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val favoriteMovieRepo: FavoriteMovieRepo) : ViewModel() {
    var movieList: LiveData<MutableList<FavoriteMovieDb>> = favoriteMovieRepo.getAll().asLiveData()

    fun addFavorite(favoriteMovieDb: FavoriteMovieDb) = viewModelScope.launch {
        favoriteMovieRepo.saveProject(favoriteMovieDb)
    }

    fun removeFavorite(favoriteMovieDb: FavoriteMovieDb) = viewModelScope.launch {
        favoriteMovieRepo.deleteProject(favoriteMovieDb)
    }

    fun generateFavoriteMovie(movie: Movie?): FavoriteMovieDb? {
        Log.d("-----model----", movie.toString())
        if (movie != null) {
            return favoriteMovieRepo.generateFavoriteMovie(
                movie.id,
                movie.vote_average,
                movie.overview,
                movie.release_date,
                movie.title,
                movie.adult,
                movie.backdrop_path,
                movie.video,
                movie.original_language,
                movie.original_title,
                movie.poster_path,
                movie.popularity,
                movie.media_type,
                movie.vote_count)
        }
        return null
    }

    fun listenFavoriteResult(): LiveData<MutableList<FavoriteMovieDb>> {
        return movieList
    }

    class FavoriteMovieViewModelFactory(private val repository: FavoriteMovieRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoriteMovieViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}