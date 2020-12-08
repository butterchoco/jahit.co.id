package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Constant

@Entity(tableName = Constant.Table.PROJECT)
data class Movie (
    @PrimaryKey var id: String,
    @ColumnInfo(name="vote_average") val voteAverage: Int,
    @ColumnInfo(name="overview") val overview: String,
    @ColumnInfo(name="release_date") val releaseDate: String,
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="adult") val adult: Boolean,
    @ColumnInfo(name="backdrop_path") val backdropPath: String,
    @ColumnInfo(name="genre_ids") val genreIds: List<String>,
    @ColumnInfo(name="video") val video: Boolean,
    @ColumnInfo(name="original_language") val originalLanguage: String,
    @ColumnInfo(name="original_title") val originalTitle: String,
    @ColumnInfo(name="poster_path") val posterPath: String,
    @ColumnInfo(name="popularity") val popularity: Float,
    @ColumnInfo(name="media_type") val mediaType: String
)