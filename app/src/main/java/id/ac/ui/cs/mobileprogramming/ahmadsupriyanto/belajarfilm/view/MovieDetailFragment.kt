package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.youtube.player.YouTubePlayerFragment
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.FavoriteMovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.FavoriteMovieViewModel.FavoriteMovieViewModelFactory
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.MovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.MovieViewModel.MovieViewModelFactory
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import java.util.*

class MovieDetailFragment : Fragment() {

    val MOVIE_OBJECT = "MovieObject"

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        FavoriteMovieViewModelFactory((activity?.application as MainApp).favoriteMovieRepository)
    }

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((activity?.application as MainApp).movieRepository)
    }

    companion object {
        fun newInstance() =
            MovieDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: Bundle? = arguments;
        if (bundle == null || !bundle.containsKey(MOVIE_OBJECT)) {
            throw IllegalArgumentException("StringList should not be null");
        }
        val movie = bundle.getParcelable<Movie>(MOVIE_OBJECT)
        movie_detail_title.text = movie?.title
        movie_detail_vote_average.text = movie?.vote_average.toString()
        movie_detail_overview.text = movie?.overview
        movie_detail_release_date.text = movie?.release_date
        DownloadImageTask(movie_thumbnail).execute(Constant.Api.BASE_POSTER_URL + "w300" + movie?.backdrop_path)
        DownloadImageTask(movie_detail_preview).execute(Constant.Api.BASE_POSTER_URL + "w300" + movie?.poster_path)
        val favoriteMovie = favoriteMovieViewModel.generateFavoriteMovie(movie)
        favoriteMovieViewModel.listenFavoriteResult().observe(viewLifecycleOwner, Observer { data ->
        data?.let {
            if (it.contains(favoriteMovie)) {
                movie_add_favorite.visibility = View.GONE
                movie_remove_favorite.visibility = View.VISIBLE
            } else {
                movie_add_favorite.visibility = View.VISIBLE
                movie_remove_favorite.visibility = View.GONE
            }
        }})

        if (movie != null) {
            movieViewModel.getMovieVideos(movie.id.toString()).observe(viewLifecycleOwner, Observer { data ->
                data?.let {
                    val YOUTUBE_VIDEO_ID = it.key
                    movie_thumbnail.setOnClickListener {
                        val youtubeIntent = Intent(activity, YoutubePlayerActivity::class.java)
                        youtubeIntent.putExtra("YOUTUBE_ID", YOUTUBE_VIDEO_ID)
                        startActivity(youtubeIntent)
                    }
                }})
        }


        movie_add_favorite.setOnClickListener {
            if (favoriteMovie != null) {
                val releaseDate = favoriteMovie.releaseDate.split("-")
                val today = Date()
                val minuteStart = today.minutes
                val hourStart = today.hours
                val dayStart = today.date
                val monthStart: Long = today.month.toLong()
                val yearStart: Long = today.year.toLong()
                val dayEnd = releaseDate[2].toInt()
                val monthEnd: Long = releaseDate[1].toLong()
                val yearEnd: Long = releaseDate[0].toLong()
                val diffDate = (activity as MainActivity).diffDateFromJNI(
                     minuteStart,
                     hourStart,
                     dayStart,
                     monthStart,
                     yearStart,
                     dayEnd,
                     monthEnd,
                     yearEnd)
//                val futureDate = Date(121, 0, 2, 15, 40) // 2 Jan 2021
//                val diffDateTest = futureDate.time - today.time
                (activity as MainActivity).scheduleJob(diffDate)
                favoriteMovieViewModel.addFavorite(favoriteMovie)
            }
        }
        movie_remove_favorite.setOnClickListener {
            if (favoriteMovie != null) {
                favoriteMovieViewModel.removeFavorite(favoriteMovie)
            }
        }
    }

}
