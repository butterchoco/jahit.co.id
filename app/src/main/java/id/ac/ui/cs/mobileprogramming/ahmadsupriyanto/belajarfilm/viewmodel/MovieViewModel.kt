package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel

import android.util.Log
import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.Constant
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.MovieVideos
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.repository.MovieRepo
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Video

class MovieViewModel(private val movieRepo: MovieRepo) : ViewModel() {
    private var trendingMovieList: MutableLiveData<List<Movie>> = MutableLiveData()
    private var upComingMovieList: MutableLiveData<List<Movie>> = MutableLiveData()
    var movieVideo: MutableLiveData<Video> = MutableLiveData()

    fun getTrendingMoviesRepository(): MutableLiveData<List<Movie>> {
        trendingMovieList = movieRepo.getTrendingMovieList(Constant.Api.API_KEY)
        return trendingMovieList
    }

    fun getMovieVideos(movieId: String): MutableLiveData<Video> {
        movieVideo = movieRepo.getMovieVideos(movieId, Constant.Api.API_KEY)
        return movieVideo
    }

    fun getUpComingMoviesRepository(): MutableLiveData<List<Movie>> {
        upComingMovieList = movieRepo.getUpcomingMovieList(Constant.Api.API_KEY)
        return upComingMovieList
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
}

