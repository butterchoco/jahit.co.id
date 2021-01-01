package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.repository

import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.dao.MovieDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.ServiceBuilder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.TrendingMovieListData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepo {
    var listOfMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getTrendingMovieList(apiKey: String): MutableLiveData<List<Movie>> {
        val retrofit =
            ServiceBuilder.buildService(
                MovieDao::class.java
            )
        retrofit.getTrendingMovieList(apiKey, "en-US", "images", "en,null").enqueue(
            object : Callback<TrendingMovieListData> {
                override fun onFailure(call: Call<TrendingMovieListData>, t: Throwable) {
                    listOfMovies.postValue(null)
                }
                override fun onResponse(call: Call<TrendingMovieListData>, response: Response<TrendingMovieListData>) {
                    val results = response.body()?.results
                    listOfMovies.postValue(results)
                }
            }
        )
        return listOfMovies
    }
}