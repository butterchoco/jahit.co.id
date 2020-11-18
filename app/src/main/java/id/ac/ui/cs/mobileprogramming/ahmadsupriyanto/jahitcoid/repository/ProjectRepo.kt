package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import java.util.*

class ProjectRepo(private val projectDao: ProjectDao) {

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
        val projectDb = ProjectDb(id = generateUuid(),
            name=name,
            category=category,
            price=price,
            amount=amount,
            address=address,
            note=note,
            preview=preview,
            status=status,
            annotation=annotation)
        projectDao.insertAll(projectDb)
    }

    fun getAll(): LiveData<List<ProjectDb>> {
        return projectDao.getAll()
    }

    private fun generateUuid(): String = UUID.randomUUID().toString()

    fun deleteProject(note: ProjectDb) {
        projectDao.delete(note)
    }

}