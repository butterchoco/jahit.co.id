package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.view.ChoiceFragment
import kotlinx.android.synthetic.main.choice_fragment.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChoiceFragment.newInstance(), "CHOICE_FRAGMENT")
                .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        customer_choice.setOnClickListener {
            val projectIntent = Intent(this, MainActivity::class.java)
            startActivity(projectIntent)
        }
    }

}