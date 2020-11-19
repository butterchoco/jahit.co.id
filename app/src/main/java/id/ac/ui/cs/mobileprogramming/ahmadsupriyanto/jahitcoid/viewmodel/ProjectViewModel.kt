package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel

import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.ProjectRepo
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class ProjectViewModel(private val projectRepo: ProjectRepo) : ViewModel() {
    var projectList: LiveData<MutableList<ProjectDb>> = projectRepo.getAll().asLiveData()

    fun saveProject(
            name: String,
            category: String,
            amount: String,
            address: String,
            note: String,
            preview: String
    ) = viewModelScope.launch {
        projectRepo.saveProject(name,
            category,
            amount,
            address,
            note,
            preview)
    }

    suspend fun deleteProject(project: ProjectDb) {
        projectRepo.deleteProject(project)
    }

    fun listenProjectsResult(): LiveData<MutableList<ProjectDb>> {
        return projectList
    }

}

class ProjectViewModelFactory(private val repository: ProjectRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProjectViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}