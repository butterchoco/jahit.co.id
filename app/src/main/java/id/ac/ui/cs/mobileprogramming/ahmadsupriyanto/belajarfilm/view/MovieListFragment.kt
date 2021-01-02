package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.adapter.TrendingMovieListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.adapter.UpcomingMovieListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.MovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.viewmodel.MovieViewModel.MovieViewModelFactory

interface OnMovieClickListener {
    fun onMovieClick(it: View, movieList: MutableList<Movie>);
}

class MovieListFragment : Fragment(), OnMovieClickListener {
    private lateinit var trendingMovieListAdapter: TrendingMovieListAdapter
    private lateinit var upcomingMovieListAdapter: UpcomingMovieListAdapter

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
        trendingMovieListAdapter = TrendingMovieListAdapter()
        trendingMovieListAdapter.setListener(this)
        val trendingRecycler: RecyclerView = movieListView.findViewById(R.id.trending_movie_list_container)
        val trendingNoItem: TextView = movieListView.findViewById(R.id.trending_movie_list_no_item)
        trendingRecycler.adapter = trendingMovieListAdapter
        trendingRecycler.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.HORIZONTAL, false
        )
        movieViewModel.getTrendingMoviesRepository().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                trendingMovieListAdapter.addMovieToList(it as MutableList<Movie>)
                if (trendingMovieListAdapter.getMovieList().isEmpty()) {
                    trendingRecycler.visibility = View.GONE
                    trendingNoItem.visibility = View.VISIBLE
                } else {
                    trendingRecycler.visibility = View.VISIBLE
                    trendingNoItem.visibility = View.GONE
                }
            }
        })

        upcomingMovieListAdapter = UpcomingMovieListAdapter()
        upcomingMovieListAdapter.setListener(this)
        val upcomingRecycler: RecyclerView = movieListView.findViewById(R.id.upcoming_movie_list_container)
        val upcomingNoItem: TextView = movieListView.findViewById(R.id.trending_movie_list_no_item)
        upcomingRecycler.adapter = upcomingMovieListAdapter
        upcomingRecycler.layoutManager = GridLayoutManager(
            activity,2
        )
        movieViewModel.getUpComingMoviesRepository().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                upcomingMovieListAdapter.addMovieToList(it as MutableList<Movie>)
                if (upcomingMovieListAdapter.getMovieList().isEmpty()) {
                    upcomingRecycler.visibility = View.GONE
                    upcomingNoItem.visibility = View.VISIBLE
                } else {
                    upcomingRecycler.visibility = View.VISIBLE
                    upcomingNoItem.visibility = View.GONE
                }
            }
        })

        return movieListView
    }

    override fun onMovieClick(it: View, movieList: MutableList<Movie>) {
        val movie = movieList.find { data ->
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