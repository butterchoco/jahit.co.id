package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel

import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Constant
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.FavoriteMovieDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.FavoriteMovieRepo
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.Movie

class FavoriteMovieViewModel(private val favoriteMovieRepo: FavoriteMovieRepo) : ViewModel() {
    var movieList: LiveData<MutableList<FavoriteMovieDb>> = favoriteMovieRepo.getAll().asLiveData()

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