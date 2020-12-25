package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel

import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Constant
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.MovieRepo
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.MovieDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb

class MovieViewModel(private val movieRepo: MovieRepo) : ViewModel() {
    var movieList: MutableLiveData<List<MovieDb>> = MutableLiveData()

    fun getMoviesRepository(): MutableLiveData<List<MovieDb>> {
        movieList = movieRepo.getTrendingMovieList(Constant.Api.API_KEY)
        return movieList
    }

}

class MovieViewModelFactory(private val repository: MovieRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}