package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb

class ProjectViewModel(private val projectDao: ProjectDao) : ViewModel() {
    private lateinit var project: LiveData<List<ProjectDb>>;

    init {
        subscribeProjectResult()
    }

    fun listenProjectsResult(): LiveData<List<ProjectDb>> {
        return project
    }

    private fun subscribeProjectResult() {
        project = Transformations.map(projectDao.getAll()) {
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