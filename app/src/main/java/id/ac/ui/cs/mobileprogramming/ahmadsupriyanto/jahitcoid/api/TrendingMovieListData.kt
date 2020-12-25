package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.api

import com.google.gson.annotations.SerializedName
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.Movie

data class TrendingMovieListData(
        @SerializedName("page") val page: Int,
        @SerializedName("results") val results: List<Movie>,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("total_results") val total_results: Int
)