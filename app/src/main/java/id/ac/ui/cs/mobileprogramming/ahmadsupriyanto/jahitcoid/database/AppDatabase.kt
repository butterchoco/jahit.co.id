package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.dao.FavoriteMovieDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(FavoriteMovieDb::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    private class WordDatabaseCallback(
       private val scope: CoroutineScope
   ) : RoomDatabase.Callback() {

       override fun onCreate(db: SupportSQLiteDatabase) {
           super.onCreate(db)
           INSTANCE?.let { database ->
               scope.launch {
                   var wordDao = database.favoriteMovieDao()
               }
           }
       }
   }

    companion object {
       @Volatile
       private var INSTANCE: AppDatabase? = null

       fun getDatabase(
           context: Context,
           scope: CoroutineScope
       ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "word_database"
                )
                 .addCallback(WordDatabaseCallback(scope))
                 .build()
                INSTANCE = instance
                // return instance
                instance
        }
     }
   }
}