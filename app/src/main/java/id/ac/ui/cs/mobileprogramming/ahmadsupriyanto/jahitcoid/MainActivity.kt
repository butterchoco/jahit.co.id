package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main.ChoiceFragment
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main.ProjectListFragment
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main.ProjectListINoItem
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main.ProjectListItemFrame
import kotlinx.android.synthetic.main.choice_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ProjectListFragment.newInstance())
                    .replace(R.id.project_list_container, ProjectListINoItem.newInstance())
                    .commitNow()
        }
    }
}