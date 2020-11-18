package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.Project

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project")
    fun getAll(): List<Project>

    @Query("SELECT * FROM project WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Project>

    @Query("SELECT * FROM project WHERE project_name LIKE :name LIMIT 1")
    fun findByName(name: String): Project

    @Insert
    fun insertAll(vararg projects: Project)

    @Delete
    fun delete(user: Project)
}