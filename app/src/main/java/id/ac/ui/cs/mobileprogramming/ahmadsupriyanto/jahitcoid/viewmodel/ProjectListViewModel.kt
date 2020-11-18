package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.Project
import java.util.*

class ProjectListViewModel : ViewModel() {
    var projectList: LinkedList<Project> = LinkedList();

    fun addProjectToList(name: String, price: Int, amount: Int, status: String, annotation: String) {
        // TODO
    }

}