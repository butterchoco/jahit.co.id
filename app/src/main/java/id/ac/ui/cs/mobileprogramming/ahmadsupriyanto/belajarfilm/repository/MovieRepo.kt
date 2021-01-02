package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.MovieVideos
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.dao.MovieDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.ServiceBuilder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.TrendingMovieListData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.UpcomingMovieListData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Video
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepo {
    var trendingListOfMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    var upcomingListOfMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val retrofit =
        ServiceBuilder.buildService(
            MovieDao::class.java
        )

    fun getTrendingMovieList(apiKey: String): MutableLiveData<List<Movie>> {
        retrofit.getTrendingMovieList(apiKey, "en-US", "images", "en,null").enqueue(
            object : Callback<TrendingMovieListData> {
                override fun onFailure(call: Call<TrendingMovieListData>, t: Throwable) {
                    trendingListOfMovies.postValue(null)
                }
                override fun onResponse(call: Call<TrendingMovieListData>, response: Response<TrendingMovieListData>) {
                    val results = response.body()?.results
                    trendingListOfMovies.postValue(results)
                }
            }
        )
        return trendingListOfMovies
    }

    fun getMovieVideos(movieId: String, apiKey: String): MutableLiveData<Video> {
        var result: MutableLiveData<Video> = MutableLiveData()
        retrofit.getMovieVideos(movieId, apiKey).enqueue(
            object : Callback<MovieVideos> {
                override fun onFailure(call: Call<MovieVideos>, t: Throwable) {
                    result.postValue(null)
                }
                override fun onResponse(call: Call<MovieVideos>, response: Response<MovieVideos>) {
                    if (response.body()?.results?.size !== 0) result.postValue(response.body()?.results?.get(0))
                    else result.postValue(null)
                }
            }
        )

        return result
    }

    fun getUpcomingMovieList(apiKey: String): MutableLiveData<List<Movie>> {
        retrofit.getUpComingMovieList(apiKey, "US").enqueue(
            object : Callback<UpcomingMovieListData> {
                override fun onFailure(call: Call<UpcomingMovieListData>, t: Throwable) {
                    upcomingListOfMovies.postValue(null)
                }
                override fun onResponse(call: Call<UpcomingMovieListData>, response: Response<UpcomingMovieListData>) {
                    val results = response.body()?.results
                    upcomingListOfMovies.postValue(results)
                }
            }
        )
        return upcomingListOfMovies
    }
}