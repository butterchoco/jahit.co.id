package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.youtube.player.*
import kotlinx.android.synthetic.main.movie_detail_fragment.*

class MainActivity : AppCompatActivity() {
    var doubleBackToExit: Boolean = false
    lateinit var _mContext: Context
    var isSetView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mContext = applicationContext
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        _mContext.registerReceiver(isInternetAvailableBroadcastReceiver(_mContext), filter);
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

    fun initActivityContentView() {
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

    fun isInternetAvailableBroadcastReceiver(_mContext: Context) = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.addFlags(Intent.FLAG_FROM_BACKGROUND)
            if (!isInternetAvailable(_mContext)){
                setContentView(R.layout.no_internet_access)
                isSetView = false
            } else {
                if (!isSetView) {
                    val mStartActivity = Intent(context, MainActivity::class.java)
                    val mPendingIntentId = 123456
                    val mPendingIntent: PendingIntent = PendingIntent.getActivity(
                        context,
                        mPendingIntentId,
                        mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT
                    )
                    val mgr: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
                    System.exit(0)
                } else {
                    initActivityContentView()
                }
            }
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var result = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                connectivityManager.run {
                    connectivityManager.activeNetworkInfo?.run {
                        result = when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }

                    }
                }
            }

            return result
    }

}