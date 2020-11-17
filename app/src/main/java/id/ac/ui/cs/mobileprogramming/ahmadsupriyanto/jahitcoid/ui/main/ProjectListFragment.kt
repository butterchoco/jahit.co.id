package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Project
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ProjectListAdapter
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import kotlinx.android.synthetic.main.project_list_fragment.*
import java.util.*

class ProjectListFragment : Fragment() {
    private lateinit var projectListAdapter: ProjectListAdapter;
    private lateinit var projectList: LinkedList<Project>;

    companion object {
        fun newInstance() = ProjectListFragment()
    }

    private lateinit var viewModel: ProjectListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TEST", "-----------------------------------")
        return inflater.inflate(R.layout.project_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
        viewModel.initiate();
        projectList = viewModel.projectList;
        Log.d("TEST", projectList.toString());
    }

    override fun onStart() {
        super.onStart()
        projectListAdapter = ProjectListAdapter(activity, projectList);
        project_list_container.adapter = projectListAdapter;
        project_list_container.layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL, false
        );
    }
}