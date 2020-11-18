package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.ProjectDao
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.Project

@Database(entities = arrayOf(Project::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}