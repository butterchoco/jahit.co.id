package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R

class ProjectListINoItem : Fragment() {

    companion object {
        fun newInstance() = ProjectListINoItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.project_list_no_item, container, false)
    }

}