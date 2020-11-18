package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.TransitionViewModel
import kotlinx.android.synthetic.main.navigation.*

class TransitionFragment : Fragment() {

    companion object {
        fun newInstance() =
            TransitionFragment()
    }

    private lateinit var viewModel: TransitionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transition_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransitionViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        project_nav.setImageResource(R.drawable.ic_project_icon)
        profile_nav.setImageResource(R.drawable.ic_profile_icon)
        transaction_nav.setImageResource(R.drawable.ic_transaction_icon)
        chat_nav.setImageResource(R.drawable.ic_chat_icon)
        activity?.supportFragmentManager?.fragments?.lastOrNull()?.let { currentFragment ->
            if (currentFragment.tag == "PROJECT_FRAGMENT") {
                project_nav.setImageResource(R.drawable.ic_project_icon_active)
            } else if (currentFragment.tag == "TRANSACTION_FRAGMENT") {
                transaction_nav.setImageResource(R.drawable.ic_transaction_icon_active)
            } else if (currentFragment.tag == "CHAT_FRAGMENT") {
                chat_nav.setImageResource(R.drawable.ic_chat_icon_active)
            } else if (currentFragment.tag == "PROFILE_FRAGMENT") {
                profile_nav.setImageResource(R.drawable.ic_profile_icon_active)
            }
        }
    }

}