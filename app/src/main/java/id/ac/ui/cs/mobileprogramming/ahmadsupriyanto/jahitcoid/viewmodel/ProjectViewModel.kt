package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.Project

class ProjectViewModel(private val projectDao: ProjectDao) : ViewModel() {
    private lateinit var project: LiveData<List<Project>>;

    init {
        subscribeProjectResult()
    }

    fun listenProjectsResult(): LiveData<List<Project>> {
        return project
    }

    private fun subscribeProjectResult() {
        project = Transformations.map(project) {
            data -> data.reversed().map { it ->
                Project(it.id, it.name, it.category, it.price, it.amount, it.address, it.note, it.preview, it.status, it.annotation)
            }
        }
    }
}