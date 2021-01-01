package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.Constant
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.DownloadImageTask
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.FavoriteMovieDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.FavoriteMovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.FavoriteMovieViewModel.FavoriteMovieViewModelFactory
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class FavoriteMovieDetailFragment : Fragment() {

    val MOVIE_OBJECT = "MovieObject"

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        FavoriteMovieViewModelFactory((activity?.application as MainApp).favoriteMovieRepository)
    }

    companion object {
        fun newInstance() =
            FavoriteMovieDetailFragment()
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
        val movie = bundle.getParcelable<FavoriteMovieDb>(MOVIE_OBJECT)
        movie_detail_title.text = movie?.title
        movie_detail_vote_average.text = movie?.voteAverage.toString()
        movie_detail_overview.text = movie?.overview
        movie_detail_release_date.text = movie?.releaseDate
        DownloadImageTask(movie_detail_preview).execute(Constant.Api.BASE_POSTER_URL + "w300" + movie?.posterPath)
        favoriteMovieViewModel.listenFavoriteResult().observe(viewLifecycleOwner, Observer { data ->
        data?.let {
            if (it.contains(movie)) {
                movie_add_favorite.visibility = View.GONE
                movie_remove_favorite.visibility = View.VISIBLE
            } else {
                movie_add_favorite.visibility = View.VISIBLE
                movie_remove_favorite.visibility = View.GONE
            }
        }})
        movie_add_favorite.setOnClickListener {
            if (movie != null) {
                favoriteMovieViewModel.addFavorite(movie)
            }
        }
        movie_remove_favorite.setOnClickListener {
            if (movie != null) {
                favoriteMovieViewModel.removeFavorite(movie)
            }
        }
    }

}