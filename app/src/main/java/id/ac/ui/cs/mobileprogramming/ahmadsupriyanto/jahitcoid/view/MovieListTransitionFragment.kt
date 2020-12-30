package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.app.AlarmManager
import android.app.PendingIntent
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
            ?.addToBackStack(null)
            ?.commit()
    }

}