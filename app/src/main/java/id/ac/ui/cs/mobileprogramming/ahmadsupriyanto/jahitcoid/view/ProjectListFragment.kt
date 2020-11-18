package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.Project
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.ProjectListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.ProjectListViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.project_list_fragment.*
import org.koin.android.architecture.ext.viewModel

class ProjectListFragment : Fragment() {
    private lateinit var projectListAdapter: ProjectListAdapter;
    private lateinit var projectList: List<Project>;

    companion object {
        fun newInstance() =
            ProjectListFragment()
    }

    private lateinit var projectListViewModel: ProjectListViewModel
    private val projectViewModel by viewModel<ProjectViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.project_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        projectListViewModel = ViewModelProvider(this).get(ProjectListViewModel::class.java)
        projectViewModel.listenProjectsResult().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                data -> projectList = data
        })
    }

    override fun onStart() {
        super.onStart();
        checkProjectListVisibility();
        initiateProjectListAdapter();

        add_project_button.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.container, AddProjectFragment.newInstance(), "MAIN_FRAGMENT")
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    fun checkProjectListVisibility() {
        if (projectList.isEmpty()) {
            project_list_container.visibility = View.GONE;
            project_list_noitem.visibility = View.VISIBLE;
        } else {
            project_list_container.visibility = View.VISIBLE;
            project_list_noitem.visibility = View.GONE;
        }
    }

    fun initiateProjectListAdapter() {
        projectListAdapter =
            ProjectListAdapter(
                activity,
                projectList
            );
        project_list_container.adapter = projectListAdapter;
        project_list_container.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL, false
        );
    }
}