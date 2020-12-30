package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R

class MovieListTransitionFragment : Fragment() {
    lateinit var _mContext: Context

    companion object {
        fun newInstance() =
            MovieListTransitionFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mContext = (activity as MainActivity).applicationContext
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        _mContext.registerReceiver(isInternetAvailableBroadcastReceiver(_mContext), filter);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_list_transition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.home_list_transition,
                MovieListFragment.newInstance(),
                "MOVIE_FRAGMENT"
            )
            ?.commit()
    }

    fun isInternetAvailableBroadcastReceiver(_mContext: Context) = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.addFlags(Intent.FLAG_FROM_BACKGROUND)
            if (!(activity as MainActivity).isInternetAvailable(_mContext)){
                activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.home_list_transition,
                    NoInternetFragment.newInstance(),
                    "NO_INTERNET_FRAGMENT"
                )
                ?.addToBackStack(null)
                ?.commit()
            } else {
                activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.home_list_transition,
                    MovieListFragment.newInstance(),
                    "MOVIE_FRAGMENT"
                )
                ?.addToBackStack(null)
                ?.commit()
            }
        }
    }

}