package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.AddProjectActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.MainApp
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.ProjectListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.ProjectViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.ProjectViewModelFactory

interface OnProjectClickListener {
    fun onProjectClick(it: View);
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
        val noItem: TextView = projectListView.findViewById(R.id.project_list_no_item)
        recycler.adapter = projectListAdapter
        recycler.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL, false
        )
        projectViewModel.listenProjectsResult().observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                projectListAdapter.addProjectToList(it)
                if (projectListAdapter.getProjectList().isEmpty()) {
                    recycler.visibility = View.GONE
                    noItem.visibility = View.VISIBLE
                } else {
                    recycler.visibility = View.VISIBLE
                    noItem.visibility = View.GONE
                }
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
                val project:ProjectDb = projectViewModel.generateProject(data.getStringExtra("project_name"),
                                        data.getStringExtra("project_category"),
                                        data.getStringExtra("project_amount"),
                                        data.getStringExtra("project_address"),
                                        data.getStringExtra("project_note"),
                                        data.getStringExtra("project_preview"))
                projectViewModel.saveProject(project)
                (activity as MainActivity).projectAddNotification(project.id)
            }
        }
    }

    override fun onProjectClick(it: View) {
        val project = projectListAdapter.getProjectList().find { data ->
            data.id == it.tag.toString()
        }
        val bundle: Bundle = Bundle()
        if (project != null) {
            bundle.putString("project_price", project.price)
            bundle.putString("project_name", project.name)
            bundle.putString("project_category", project.category)
            bundle.putString("project_amount", project.amount)
            bundle.putString("project_address", project.address)
            bundle.putString("project_note", project.note)
            bundle.putString("project_preview", project.preview)
            bundle.putString("project_quotation", project.quotation)
            bundle.putString("project_vendor", project.vendor)
            bundle.putString("project_startDate", project.startDate)
            bundle.putString("project_endDate", project.endDate)
        }
        val projectDetailFragment: ProjectDetailFragment = ProjectDetailFragment.newInstance()
        projectDetailFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.project_list_transition,
                projectDetailFragment,
                "PROJECT_DETAIL_FRAGMENT"
            )
            ?.addToBackStack(null)
            ?.commit()
    }
}