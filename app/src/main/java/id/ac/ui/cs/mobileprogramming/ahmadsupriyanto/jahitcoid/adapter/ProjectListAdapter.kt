package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.R
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.adapter.ProjectListAdapter.ProjectListViewHolder
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb

class ProjectListAdapter(
    context: Context?,
    projectList: List<ProjectDb>
) : RecyclerView.Adapter<ProjectListViewHolder>() {

    private val projectList: List<ProjectDb>
    private val mInflater: LayoutInflater

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectListViewHolder {
        val projectListItemView = mInflater.inflate(
            R.layout.project_list_item,
            parent, false
        )
        return ProjectListViewHolder(projectListItemView, this)
    }

    override fun onBindViewHolder(
        holder: ProjectListViewHolder,
        position: Int
    ) {
        val project = projectList[position]
        holder.projectId.setOnClickListener { it ->
            Log.d("--------------", it.tag.toString())
        }
        holder.projectId.tag = project.id
        holder.projectName.setText(project.name);
        holder.projectPrice.setText(project.price);
        holder.projectAmount.setText(project.amount);
        holder.projectAnnotation.setText(project.annotation);
        holder.projectStatus.setText(project.status);
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    class ProjectListViewHolder(
        itemView: View,
        val mAdapter: ProjectListAdapter
    ) : RecyclerView.ViewHolder(itemView) {
        val projectId: MaterialCardView
        var projectName: TextView
        val projectPrice: TextView
        val projectAmount: TextView
        val projectStatus: TextView
        val projectAnnotation: TextView

        init {
            projectId = itemView.findViewById(R.id.project_card)
            projectName = itemView.findViewById(R.id.project_name)
            projectPrice = itemView.findViewById(R.id.project_price)
            projectAmount = itemView.findViewById(R.id.project_amount)
            projectStatus = itemView.findViewById(R.id.project_status)
            projectAnnotation = itemView.findViewById(R.id.project_annotation)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
        this.projectList = projectList
    }
}