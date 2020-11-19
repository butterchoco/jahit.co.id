package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.AddProjectActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.ProjectListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.project_list_fragment.*
import kotlinx.android.synthetic.main.project_list_item.*
import org.koin.android.architecture.ext.viewModel

class ProjectListFragment : Fragment() {
    private lateinit var projectListAdapter: ProjectListAdapter
    private var projectList: List<ProjectDb> = listOf()

    companion object {
        fun newInstance() =
            ProjectListFragment()
    }

    private val projectViewModel by viewModel<ProjectViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        projectViewModel.listenProjectsResult().observe(this, Observer { data ->
            data?.let {
                projectList = data
                initProjectList()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.project_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initiateProjectListAdapter()
        checkProjectListVisibility()
    }

    override fun onStart() {
        super.onStart()
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

    private fun checkProjectListVisibility() {
        if (projectList.isEmpty()) {
            project_list_container.visibility = View.GONE
            project_list_noitem.visibility = View.VISIBLE
        } else {
            project_list_container.visibility = View.VISIBLE
            project_list_noitem.visibility = View.GONE
        }
    }

    private fun initiateProjectListAdapter() {
        projectListAdapter =
            ProjectListAdapter(
                activity,
                projectList
            )
        project_list_container.adapter = projectListAdapter
        project_list_container.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL, false
        )
    }

    fun initProjectList() {
        initiateProjectListAdapter()
        checkProjectListVisibility()
        add_project_button.setOnClickListener{
            val addProjectIntent = Intent(activity, AddProjectActivity::class.java)
            startActivityForResult(addProjectIntent, 1)
        }
    }
}