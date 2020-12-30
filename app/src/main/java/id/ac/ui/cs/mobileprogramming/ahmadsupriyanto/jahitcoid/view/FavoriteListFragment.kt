package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.FavoriteMovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.FavoriteMovieViewModel.FavoriteMovieViewModelFactory

class FavoriteListFragment : Fragment() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        FavoriteMovieViewModelFactory((activity?.application as MainApp).favoriteMovieRepository)
    }

    companion object {
        fun newInstance() =
            FavoriteListFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val favoriteMovieListView = inflater.inflate(R.layout.transaction_list_fragment, container, false)
//        movieListAdapter = MovieListAdapter()
//        movieListAdapter.setListener(this)
//        val recycler: RecyclerView = favoriteMovieListView.findViewById(R.id.movie_list_container)
//        val noItem: TextView = favoriteMovieListView.findViewById(R.id.movie_list_no_item)
//        recycler.adapter = movieListAdapter
//        recycler.layoutManager = LinearLayoutManager(
//            activity,
//            RecyclerView.HORIZONTAL, false
//        )
        favoriteMovieViewModel.listenFavoriteResult().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                Log.d("-----------", it.toString())
//                movieListAdapter.addMovieToList(it as MutableList<Movie>)
//                if (movieListAdapter.getMovieList().isEmpty()) {
//                    recycler.visibility = View.GONE
//                    noItem.visibility = View.VISIBLE
//                } else {
//                    recycler.visibility = View.VISIBLE
//                    noItem.visibility = View.GONE
//                }
            }
        })

        return favoriteMovieListView
    }

}