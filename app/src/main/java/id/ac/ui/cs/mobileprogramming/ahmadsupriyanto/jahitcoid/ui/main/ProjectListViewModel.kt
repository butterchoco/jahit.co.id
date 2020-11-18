package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Project
import java.util.*

class ProjectListViewModel : ViewModel() {
    var projectList: LinkedList<Project> = LinkedList();

    fun addProjectToList(name: String, price: Int, amount: Int, status: String, annotation: String) {
        var project = Project(name, price, amount, status, annotation);
        projectList.addLast(project)
    }

}