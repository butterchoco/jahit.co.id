package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel

import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.FavoriteMovieDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.FavoriteMovieRepo
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.Movie
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val favoriteMovieRepo: FavoriteMovieRepo) : ViewModel() {
    var movieList: LiveData<MutableList<FavoriteMovieDb>> = favoriteMovieRepo.getAll().asLiveData()

    fun addFavorite(favoriteMovieDb: FavoriteMovieDb) = viewModelScope.launch {
        favoriteMovieRepo.saveProject(favoriteMovieDb)
    }

    fun generateFavoriteMovie(movie: Movie?): FavoriteMovieDb {
        return favoriteMovieRepo.generateFavoriteMovie(
            movie?.id!!,
            movie.vote_average,
            movie.overview.toString(),
            movie.release_date.toString(),
            movie.title.toString(),
            movie.adult,
            movie.backdrop_path.toString(),
            movie.video,
            movie.original_language.toString(),
            movie.original_title.toString(),
            movie.poster_path.toString(),
            movie.popularity,
            movie.media_type.toString(),
            movie.vote_count)
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