package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.ChoiceViewModel

class ChoiceFragment : Fragment() {

    companion object {
        fun newInstance() =
            ChoiceFragment()
    }

    private lateinit var viewModel: ChoiceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.choice_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChoiceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}