package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api

import com.google.gson.annotations.SerializedName
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Video

data class MovieVideos (
    @SerializedName("id") val id: Int,
    @SerializedName("results") val results: List<Video>
)