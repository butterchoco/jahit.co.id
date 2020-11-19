package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.AddProjectActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.ProjectListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.ProjectViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.ProjectViewModelFactory
import kotlinx.android.synthetic.main.project_list_fragment.*
import kotlinx.android.synthetic.main.project_list_item.*
import org.koin.android.architecture.ext.viewModel

interface OnProjectClickListener {
    fun onProjectClick();
}

class ProjectListFragment : Fragment(), OnProjectClickListener {
    private lateinit var projectListAdapter: ProjectListAdapter

    companion object {
        fun newInstance() =
            ProjectListFragment()
    }

    private val projectViewModel: ProjectViewModel by viewModels {
        ProjectViewModelFactory((activity?.application as MainApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val projectListView = inflater.inflate(R.layout.project_list_fragment, container, false)

        projectListAdapter = ProjectListAdapter()
        projectListAdapter.setListener(this)
        val recycler: RecyclerView = projectListView.findViewById(R.id.project_list_container)
        recycler.adapter = projectListAdapter
        recycler.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL, false
        )
        projectViewModel.listenProjectsResult().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                projectListAdapter.addProjectToList(it)
            }
        })

        projectListView.findViewById<FloatingActionButton>(R.id.add_project_button).setOnClickListener{
            val addProjectIntent = Intent(activity, AddProjectActivity::class.java)
            startActivityForResult(addProjectIntent, 1)
        }

        return projectListView
    }

    private val newWordActivityRequestCode = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            if (!data?.extras?.isEmpty!!) {
                projectViewModel.saveProject(data.getStringExtra("project_name"),
                data.getStringExtra("project_category"),
                data.getStringExtra("project_amount"),
                data.getStringExtra("project_address"),
                data.getStringExtra("project_note"),
                data.getStringExtra("project_preview"))
            }
        }
    }

    override fun onProjectClick() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.transition_container,
                ProjectDetailFragment.newInstance(),
                "PROJECT_DETAIL_FRAGMENT"
            )
            ?.addToBackStack(null)
            ?.commit()
    }
}