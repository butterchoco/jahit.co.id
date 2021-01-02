package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api

import com.google.gson.annotations.SerializedName
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.RangeDates

data class UpcomingMovieListData(
    @SerializedName("dates") val dates: RangeDates,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val total_results: Int
)