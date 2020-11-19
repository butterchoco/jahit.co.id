package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao

import androidx.room.*
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project_table")
    fun getAll(): Flow<List<ProjectDb>>

    @Query("SELECT * FROM project_table WHERE project_name LIKE :name LIMIT 1")
    fun findByName(name: String): ProjectDb

    @Insert
    suspend fun insertAll(vararg projects: ProjectDb)

    @Delete
    suspend fun delete(project: ProjectDb)
}