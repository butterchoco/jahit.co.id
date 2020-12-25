package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.MovieListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.Movie
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.MovieViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.MovieViewModelFactory

interface OnMovieClickListener {
    fun onMovieClick(it: View);
}

class MovieListFragment : Fragment(), OnMovieClickListener {
    private lateinit var movieListAdapter: MovieListAdapter
    lateinit var _mContext: Context
    private val newWordActivityRequestCode = 1

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((activity?.application as MainApp).movieRepository)
    }

    companion object {
        fun newInstance() =
            MovieListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filter = IntentFilter()
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        _mContext = activity?.applicationContext!!
        _mContext.registerReceiver(internetBroadcastReceiver(), filter);
    }

    private fun internetBroadcastReceiver() = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
                val extraWifiState = intent.getIntExtra(
                    WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN
                )
                if (extraWifiState == WifiManager.WIFI_STATE_ENABLED) {
                    Toast.makeText(
                        context, R.string.internet_connected,
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (extraWifiState == WifiManager.WIFI_STATE_DISABLED) {
                    Toast.makeText(
                        context, R.string.internet_disconnected,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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
                Log.d("---------------", it.toString())
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

//        movieListView.findViewById<FloatingActionButton>(R.id.add_project_button).setOnClickListener{
//            val addMovieIntent = Intent(activity, AddMovieActivity::class.java)
//            startActivityForResult(addMovieIntent, 1)
//        }

        return movieListView
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            if (!data?.extras?.isEmpty!!) {
//                val project:Movie = movieViewModel.generateProject(data.getStringExtra("project_name"),
//                                        data.getStringExtra("project_category"),
//                                        data.getStringExtra("project_amount"),
//                                        data.getStringExtra("project_address"),
//                                        data.getStringExtra("project_note"),
//                                        data.getStringExtra("project_preview"))
//                movieViewModel.saveProject(project)
//                (activity as MainActivity).projectAddNotification(project.id)
//            }
//        }
//    }

    override fun onMovieClick(it: View) {
        val movie = movieListAdapter.getMovieList().find { data ->
            data.id == it.tag
        }
        val bundle = Bundle()
        if (movie != null) {
            bundle.putString("movie_title", movie.title)
            bundle.putString("movie_vote_average", movie.vote_average.toString())
            bundle.putString("movie_overview", movie.overview)
            bundle.putString("movie_release_date", movie.release_date)
            bundle.putString("movie_poster_path", movie.poster_path)
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