package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Constant
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.DownloadImageTask
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.FavoriteMovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.FavoriteMovieViewModel.FavoriteMovieViewModelFactory
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class MovieDetailFragment : Fragment() {

    val MOVIE_OBJECT = "MovieObject"

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
        val bundle: Bundle? = getArguments();
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
        movie_add_favorite.setOnClickListener {
            favoriteMovieViewModel.addFavorite(favoriteMovie)
        }
    }

}