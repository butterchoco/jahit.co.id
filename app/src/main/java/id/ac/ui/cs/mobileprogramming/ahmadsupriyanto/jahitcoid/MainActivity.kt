package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database.AppDatabase
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view.ChoiceFragment
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view.ProjectListFragment
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view.TransitionFragment
import kotlinx.android.synthetic.main.choice_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ChoiceFragment.newInstance(), "CHOICE_FRAGMENT")
                .addToBackStack(null)
                .commit()
        }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "jahit-database"
        ).build()
    }

    override fun onStart() {
        super.onStart()
        customerChoice.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TransitionFragment.newInstance(), "MAIN_FRAGMENT")
                .add(
                    R.id.transition_container,
                    ProjectListFragment.newInstance(),
                    "PROJECT_FRAGMENT"
                )
                .commitNow()
        }
    }
}