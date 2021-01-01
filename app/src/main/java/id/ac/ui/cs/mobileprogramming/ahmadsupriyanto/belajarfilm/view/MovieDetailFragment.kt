package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.FavoriteMovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.FavoriteMovieViewModel.FavoriteMovieViewModelFactory
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class MovieDetailFragment : Fragment() {

    val MOVIE_OBJECT = "MovieObject"
    private val RECOVERY_DIALOG_REQUEST = 1
    lateinit var YOUTUBE_VIDEO_ID: String

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        FavoriteMovieViewModelFactory((activity?.application as MainApp).favoriteMovieRepository)
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
        YOUTUBE_VIDEO_ID = "JhskO-yBtXw"
        (activity as MainActivity).initYoutubeFragment(YOUTUBE_VIDEO_ID)
        movie_add_favorite.setOnClickListener {
            if (favoriteMovie != null) {
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
