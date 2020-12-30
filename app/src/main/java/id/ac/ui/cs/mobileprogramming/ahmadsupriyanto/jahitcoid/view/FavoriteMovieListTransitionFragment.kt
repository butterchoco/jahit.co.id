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

class FavoriteMovieListTransitionFragment : Fragment() {

    companion object {
        fun newInstance() =
            FavoriteMovieListTransitionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_list_transition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.favorite_list_transition,
                FavoriteListFragment.newInstance(),
                "FAVORITE_MOVIE_FRAGMENT"
            )
            ?.addToBackStack(null)
            ?.commit()
    }

}