package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    var doubleBackToExit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_project, R.id.navigation_transaction, R.id.navigation_chat
            )
        )
        setSupportActionBar(findViewById(R.id.main_toolbar))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (doubleBackToExit) {
            finishAffinity()
            return
        }
        this.doubleBackToExit = true
        Toast.makeText(this, R.string.double_back_pressed_warning, Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExit = false }, 2000)
    }

    fun projectAddNotification(projectId: String) {
        var builder = NotificationCompat.Builder(this, projectId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(applicationContext.resources.getString(R.string.project_add_notification_title))
                            .setContentText(applicationContext.resources.getString(R.string.project_add_notification_content))
                            .setDefaults(NotificationCompat.DEFAULT_ALL)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
        val notifId = projectId.replace("[^\\d.]".toRegex(), "").substring(0,4)
        with(NotificationManagerCompat.from(this)) {
            notify(notifId.toInt(), builder.build())
        }
    }
}