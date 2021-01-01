package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.R

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