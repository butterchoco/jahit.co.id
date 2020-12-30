package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

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
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.FavoriteMovieListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.FavoriteMovieDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.FavoriteMovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.FavoriteMovieViewModel.FavoriteMovieViewModelFactory

class FavoriteListFragment : Fragment(), OnMovieClickListener {
    private lateinit var favoriteMovieListAdapter: FavoriteMovieListAdapter
    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        FavoriteMovieViewModelFactory((activity?.application as MainApp).favoriteMovieRepository)
    }

    companion object {
        fun newInstance() =
            FavoriteListFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val favoriteMovieListView = inflater.inflate(R.layout.favorite_list_fragment, container, false)
        favoriteMovieListAdapter = FavoriteMovieListAdapter()
        favoriteMovieListAdapter.setListener(this)
        val recycler: RecyclerView = favoriteMovieListView.findViewById(R.id.favorite_movie_list_container)
        val noItem: TextView = favoriteMovieListView.findViewById(R.id.favorite_movie_list_no_item)
        recycler.adapter = favoriteMovieListAdapter
        recycler.layoutManager = GridLayoutManager(
            activity, 2,
            RecyclerView.VERTICAL, false
        )
        favoriteMovieViewModel.listenFavoriteResult().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                favoriteMovieListAdapter.addMovieToList(it as MutableList<FavoriteMovieDb>)
                if (favoriteMovieListAdapter.getMovieList().isEmpty()) {
                    recycler.visibility = View.GONE
                    noItem.visibility = View.VISIBLE
                } else {
                    recycler.visibility = View.VISIBLE
                    noItem.visibility = View.GONE
                }
            }
        })

        return favoriteMovieListView
    }

    override fun onMovieClick(it: View) {
        val movie = favoriteMovieListAdapter.getMovieList().find { data ->
            data.id == it.tag
        }
        val bundle = Bundle()
        if (movie != null) {
            bundle.putParcelable("MovieObject", movie)
        }
        val favoriteMovieDetailFragment: FavoriteMovieDetailFragment = FavoriteMovieDetailFragment.newInstance()
        favoriteMovieDetailFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.favorite_list_transition,
                favoriteMovieDetailFragment,
                "FAVORITE_MOVIE_DETAIL_FRAGMENT"
            )
            ?.addToBackStack(null)
            ?.commit()
    }
}