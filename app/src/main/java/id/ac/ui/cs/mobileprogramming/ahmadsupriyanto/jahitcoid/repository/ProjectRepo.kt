package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import kotlinx.coroutines.flow.Flow
import java.util.*

class ProjectRepo(private val projectDao: ProjectDao) {

    suspend fun saveProject(projectDb: ProjectDb) {
        projectDao.insertAll(projectDb)
    }

    fun generateProject(name: String,
            category: String,
            amount: String,
            address: String,
            note: String,
            preview: String): ProjectDb {
        return ProjectDb(
            id=generateUuid(),
            name=name,
            category=category,
            price="",
            amount=amount,
            address=address,
            note=note,
            preview=preview,
            status="PENAWARAN_TERBUKA",
            annotation="Chat dengan kami",
            vendor = "",
            quotation = "",
            startDate = "",
            endDate = "")
    }

    fun getAll(): Flow<MutableList<ProjectDb>> {
        return projectDao.getAll()
    }

    private fun generateUuid(): String = UUID.randomUUID().toString()

    suspend fun deleteProject(note: ProjectDb) {
        projectDao.delete(note)
    }

}