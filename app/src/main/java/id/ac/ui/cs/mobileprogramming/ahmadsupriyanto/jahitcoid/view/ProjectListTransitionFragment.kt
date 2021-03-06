package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R

class ProjectListTransitionFragment : Fragment() {

    companion object {
        fun newInstance() =
            ProjectListTransitionFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.project_list_transition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.project_list_transition,
                ProjectListFragment.newInstance(),
                "PROJECT_FRAGMENT"
            )
            ?.commit()
    }

}