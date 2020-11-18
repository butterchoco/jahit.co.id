package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.ProjectDb

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project_table")
    fun getAll(): LiveData<List<ProjectDb>>

    @Query("SELECT * FROM project_table WHERE project_name LIKE :name LIMIT 1")
    fun findByName(name: String): ProjectDb

    @Insert
    fun insertAll(vararg projects: ProjectDb)

    @Delete
    fun delete(project: ProjectDb)
}