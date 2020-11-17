package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main.ChoiceFragment
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main.ProjectListFragment
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main.ProjectListINoItem
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.ui.main.TransitionFragment
import kotlinx.android.synthetic.main.choice_fragment.*
import kotlinx.android.synthetic.main.project_list_fragment.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ChoiceFragment.newInstance(), "MAIN_FRAGMENT")
                .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        customerChoice.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TransitionFragment.newInstance(), "MAIN_FRAGMENT")
                .add(
                    R.id.transition_container,
                    ProjectListFragment.newInstance(),
                    "TRANSITION_FRAGMENT"
                )
                .commitNow()
        }
    }
}