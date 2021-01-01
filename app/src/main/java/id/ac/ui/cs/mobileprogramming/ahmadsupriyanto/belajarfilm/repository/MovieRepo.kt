package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.MovieVideos
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.dao.MovieDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.ServiceBuilder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.TrendingMovieListData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Video
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepo {
    var listOfMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val retrofit =
        ServiceBuilder.buildService(
            MovieDao::class.java
        )

    fun getTrendingMovieList(apiKey: String): MutableLiveData<List<Movie>> {
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

    fun getMovieVideos(movieId: String, apiKey: String): MutableLiveData<Video> {
        var result: MutableLiveData<Video> = MutableLiveData()
        retrofit.getMovieVideos(movieId, apiKey).enqueue(
            object : Callback<MovieVideos> {
                override fun onFailure(call: Call<MovieVideos>, t: Throwable) {
                    result.postValue(null)
                }
                override fun onResponse(call: Call<MovieVideos>, response: Response<MovieVideos>) {
                    result.postValue(response.body()?.results?.get(0))
                }
            }
        )

        return result
    }
}