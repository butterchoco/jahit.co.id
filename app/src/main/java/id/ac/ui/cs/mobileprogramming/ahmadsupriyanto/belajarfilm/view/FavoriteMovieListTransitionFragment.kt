package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.R

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