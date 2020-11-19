package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.ProjectListAdapter.ProjectListViewHolder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view.OnProjectClickListener

class ProjectListAdapter : ListAdapter<ProjectDb, ProjectListViewHolder>(ProjectsComparator()) {

    private var projectList: MutableList<ProjectDb> = mutableListOf()
    private lateinit var listener: OnProjectClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectListViewHolder {
        return ProjectListViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: ProjectListViewHolder,
        position: Int
    ) {
        val project = projectList[position]
        holder.bind(project)
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener.onProjectClick()
            }
        }
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    fun addProjectToList(projList: MutableList<ProjectDb>) {
        projectList.addAll(projList)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnProjectClickListener) {
        this.listener = listener
    }

    fun getProjectList(): MutableList<ProjectDb> {
        return projectList
    }

    class ProjectListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var projectId:MaterialCardView = itemView.findViewById(R.id.project_card)
        private var projectName: TextView = itemView.findViewById(R.id.project_name)
        private var projectPrice: TextView = itemView.findViewById(R.id.project_price)
        private var projectAmount: TextView = itemView.findViewById(R.id.project_amount)
        private var projectStatus: TextView = itemView.findViewById(R.id.project_status)
        private var projectAnnotation: TextView = itemView.findViewById(R.id.project_annotation)

        fun bind(project: ProjectDb) {
            projectId.tag = project.id
            projectName.text = project.name;
            projectPrice.text = project.price;
            projectAmount.text = project.amount;
            projectAnnotation.text = project.annotation;
            projectStatus.text = project.status;
        }

        companion object {
            fun create(parent: ViewGroup): ProjectListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.project_list_item, parent, false)
                return ProjectListViewHolder(view)
            }
        }
    }

    class ProjectsComparator : DiffUtil.ItemCallback<ProjectDb>() {
        override fun areItemsTheSame(oldItem: ProjectDb, newItem: ProjectDb): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProjectDb, newItem: ProjectDb): Boolean {
            return oldItem.id == newItem.id
        }
    }

}