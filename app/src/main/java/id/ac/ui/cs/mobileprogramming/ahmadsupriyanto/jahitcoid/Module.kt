package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import androidx.room.Room
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.AppDatabase
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.repository.FavoriteMovieRepo
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.viewmodel.FavoriteMovieViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val projectModule = applicationContext {

    // Create Database
    bean(name = Constant.Koin.DATABASE_DI) {
        Room
            .databaseBuilder(
                get(Constant.Koin.CONTEXT_APP_DI),
                AppDatabase::class.java,
                Constant.Table.DB_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    // Create NoteRepository
    bean { get<AppDatabase>(Constant.Koin.DATABASE_DI).favoriteMovieDao() }
    bean {
        FavoriteMovieRepo(
            get()
        )
    }

    // Create FavoriteMovieViewModel
    viewModel { FavoriteMovieViewModel(get()) }

}