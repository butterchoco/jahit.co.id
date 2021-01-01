package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.dao

import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api.TrendingMovieListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieDao {
    @Headers ("Content-Type: application/json")
    @GET("trending/movie/day")
    fun getTrendingMovieList(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("append_to_response") appendToResponse: String, @Query("include_image_language") includeImageLanguage: String): Call<TrendingMovieListData>
}