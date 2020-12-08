package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestApi {
    @Headers ("Content-Type: application/json")
    @GET("trending/movie/day")
    fun getTrendingMovieList(@Query("api_key") apiKey: String): Call<TrendingMovieListData>
}