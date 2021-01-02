package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

internal class ReleaseDateTrackJobService : JobService() {
    var mNotifyManager: NotificationManager? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartJob(jobParameters: JobParameters): Boolean {

        createNotificationChannel()

        val contentPendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder: Notification.Builder =
            Notification.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Test")
                .setContentText("testsetest")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
        mNotifyManager?.notify(0, builder.build())
        return false
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        return false
    }

    fun createNotificationChannel() {

        mNotifyManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "testets",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.setLightColor(Color.RED)
            notificationChannel.enableVibration(true)
            notificationChannel.setDescription("aokke")
            mNotifyManager?.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }
}