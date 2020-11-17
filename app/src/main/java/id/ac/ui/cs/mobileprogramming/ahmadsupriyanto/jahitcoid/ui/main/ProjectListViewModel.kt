package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Project
import java.util.*

class ProjectListViewModel : ViewModel() {
    var projectList: LinkedList<Project> = LinkedList();

    fun initiate() {
        for (i in 0..19) {
            var project = Project("test" + i, i, i, "test" + i, "test" + i);
            projectList.addLast(project)
        }
            Log.d("TEST", "testsetets")
    }

}