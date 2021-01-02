package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RangeDates(
    val minimum: String,
    val maximum: String
): Parcelable