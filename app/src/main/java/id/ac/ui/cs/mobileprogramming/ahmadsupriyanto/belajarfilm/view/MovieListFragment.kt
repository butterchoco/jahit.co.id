package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.MainActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.adapter.MovieListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.MovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.MovieViewModel.MovieViewModelFactory
import java.util.*

interface OnMovieClickListener {
    fun onMovieClick(it: View);
}

class MovieListFragment : Fragment(), OnMovieClickListener {
    private lateinit var movieListAdapter: MovieListAdapter

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((activity?.application as MainApp).movieRepository)
    }

    companion object {
        fun newInstance() =
            MovieListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieListView = inflater.inflate(R.layout.home_list_fragment, container, false)
        movieListAdapter = MovieListAdapter()
        movieListAdapter.setListener(this)
        val recycler: RecyclerView = movieListView.findViewById(R.id.movie_list_container)
        val noItem: TextView = movieListView.findViewById(R.id.movie_list_no_item)
        recycler.adapter = movieListAdapter
        recycler.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL, false
        )
        movieViewModel.getMoviesRepository().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                movieListAdapter.addMovieToList(it as MutableList<Movie>)
                if (movieListAdapter.getMovieList().isEmpty()) {
                    recycler.visibility = View.GONE
                    noItem.visibility = View.VISIBLE
                } else {
                    recycler.visibility = View.VISIBLE
                    noItem.visibility = View.GONE
                }
            }
        })

        return movieListView
    }

    override fun onMovieClick(it: View) {
        val movie = movieListAdapter.getMovieList().find { data ->
            data.id == it.tag
        }
        val bundle = Bundle()
        if (movie != null) {
            bundle.putParcelable("MovieObject", movie)
        }
        val movieDetailFragment: MovieDetailFragment = MovieDetailFragment.newInstance()
        movieDetailFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.home_list_transition,
                movieDetailFragment,
                "MOVIE_DETAIL_FRAGMENT"
            )
            ?.addToBackStack(null)
            ?.commit()
    }
}