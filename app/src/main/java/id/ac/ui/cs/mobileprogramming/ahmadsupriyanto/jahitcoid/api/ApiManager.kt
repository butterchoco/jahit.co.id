package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.api

import android.util.Log
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager {
    fun getTrendingMovieList(apiKey: String,onResult: (List<Movie>?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getTrendingMovieList(apiKey).enqueue(
            object : Callback<TrendingMovieListData> {
                override fun onFailure(call: Call<TrendingMovieListData>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<TrendingMovieListData>, response: Response<TrendingMovieListData>) {
                    val results = response.body()?.results
                    onResult(results)
                }
            }
        )
    }
}