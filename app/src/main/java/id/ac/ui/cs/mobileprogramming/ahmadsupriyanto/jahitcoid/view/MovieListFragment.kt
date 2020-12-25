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
import androidx.recyclerview.widget.GridLayoutManager
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
    private lateinit var projectListAdapter: MovieListAdapter
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
        val projectListView = inflater.inflate(R.layout.project_list_fragment, container, false)
        projectListAdapter = MovieListAdapter()
        projectListAdapter.setListener(this)
        val recycler: RecyclerView = projectListView.findViewById(R.id.project_list_container)
        val noItem: TextView = projectListView.findViewById(R.id.project_list_no_item)
        recycler.adapter = projectListAdapter
        recycler.layoutManager = GridLayoutManager(
            activity, 2,
            RecyclerView.VERTICAL, false
        )
        movieViewModel.getMoviesRepository().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                Log.d("---------------", it.toString())
                projectListAdapter.addMovieToList(it as MutableList<Movie>)
                if (projectListAdapter.getMovieList().isEmpty()) {
                    recycler.visibility = View.GONE
                    noItem.visibility = View.VISIBLE
                } else {
                    recycler.visibility = View.VISIBLE
                    noItem.visibility = View.GONE
                }
            }
        })

//        projectListView.findViewById<FloatingActionButton>(R.id.add_project_button).setOnClickListener{
//            val addMovieIntent = Intent(activity, AddMovieActivity::class.java)
//            startActivityForResult(addMovieIntent, 1)
//        }

        return projectListView
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
        val project = projectListAdapter.getMovieList().find { data ->
            data.id == it.tag
        }
        val bundle: Bundle = Bundle()
        if (project != null) {
//            bundle.putString("project_price", project.price)
//            bundle.putString("project_name", project.name)
//            bundle.putString("project_category", project.category)
//            bundle.putString("project_amount", project.amount)
//            bundle.putString("project_address", project.address)
//            bundle.putString("project_note", project.note)
//            bundle.putString("project_preview", project.preview)
//            bundle.putString("project_quotation", project.quotation)
//            bundle.putString("project_vendor", project.vendor)
//            bundle.putString("project_startDate", project.startDate)
//            bundle.putString("project_endDate", project.endDate)
        }
        val projectDetailFragment: MovieDetailFragment = MovieDetailFragment.newInstance()
        projectDetailFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.project_list_transition,
                projectDetailFragment,
                "PROJECT_DETAIL_FRAGMENT"
            )
            ?.addToBackStack(null)
            ?.commit()
    }
}