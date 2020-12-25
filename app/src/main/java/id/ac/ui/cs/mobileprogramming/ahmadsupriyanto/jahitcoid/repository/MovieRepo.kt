package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.MovieDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.api.ServiceBuilder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.api.TrendingMovieListData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.Movie
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
                    Log.d("results-----------", results.toString())
                    listOfMovies.postValue(results)
                }
            }
        )
        return listOfMovies
    }
}