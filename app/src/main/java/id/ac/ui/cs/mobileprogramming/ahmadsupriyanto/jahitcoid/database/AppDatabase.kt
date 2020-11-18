package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.ProjectDao

@Database(entities = arrayOf(ProjectDb::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}