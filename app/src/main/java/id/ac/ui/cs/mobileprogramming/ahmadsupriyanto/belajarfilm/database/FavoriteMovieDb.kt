package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.Constant
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constant.Table.MOVIE)
data class FavoriteMovieDb (
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name="vote_average") val voteAverage: Double,
    @ColumnInfo(name="overview") val overview: String,
    @ColumnInfo(name="release_date") val releaseDate: String,
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="adult") val adult: Boolean,
    @ColumnInfo(name="backdrop_path") val backdropPath: String,
    @ColumnInfo(name="video") val video: Boolean,
    @ColumnInfo(name="original_language") val originalLanguage: String,
    @ColumnInfo(name="original_title") val originalTitle: String,
    @ColumnInfo(name="poster_path") val posterPath: String,
    @ColumnInfo(name="popularity") val popularity: Double,
    @ColumnInfo(name="media_type") val mediaType: String,
    @ColumnInfo(name="vote_count") val voteCount: Int
) : Parcelable