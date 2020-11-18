package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.ProjectRepo

class ProjectViewModel(private val projectRepo: ProjectRepo) : ViewModel() {
    private lateinit var project: LiveData<List<ProjectDb>>;

    init {
        subscribeProjectResult()
    }

    fun saveProject(
            name: String,
            category: String,
            price: String,
            amount: String,
            address: String,
            note: String,
            preview: String,
            status: String,
            annotation: String
    ) {
        projectRepo.saveProject(name,
            category,
            price,
            amount,
            address,
            note,
            preview,
            status,
            annotation)
    }

    fun deleteProject(project: ProjectDb) {
        projectRepo.deleteProject(project)
    }

    fun listenProjectsResult(): LiveData<List<ProjectDb>> {
        return project
    }

    private fun subscribeProjectResult() {
        project = Transformations.map(projectRepo.getAll()) {
            data -> data.reversed().map { it ->
            ProjectDb(
                it.id,
                it.name,
                it.category,
                it.price,
                it.amount,
                it.address,
                it.note,
                it.preview,
                it.status,
                it.annotation
            )
            }
        }
    }
}