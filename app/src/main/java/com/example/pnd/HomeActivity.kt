package com.example.pnd

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }


    override fun onStart() {
        super.onStart()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btnSendNotify.setOnClickListener {
            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
            addNotification()
        }
    }

    private fun addNotification() {
        // pendingIntent is an intent for future use i.e after
        // the notification is clicked, this intent will come into action
        val intent = Intent(this, NotificationView::class.java)

        // FLAG_UPDATE_CURRENT specifies that if a previous
        // PendingIntent already exists, then the current one
        // will update it with the latest intent
        // 0 is the request code, using it later with the
        // same method again will get back the same pending
        // intent for future reference
        // intent passed here is to our afterNotification class
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // RemoteViews are used to use the content of
        // some different layout apart from the current activity layout
//        val contentView = RemoteViews(packageName, R.layout.activity_main)

        // checking if android version is greater than oreo(API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText(
                    "TEXT"
                )
                .setContentText("sample text for notify")
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_background
                    )
                )
                .setContentIntent(pendingIntent)
        } else {
            builder = Notification.Builder(this)
                .setContentTitle("Tittle")
                .setContentText("sample text for notify")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_background
                    )
                )
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())
    }


//        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
//            .setSmallIcon(R.drawable.ic_launcher_foreground) //set icon for notification
//            .setContentTitle("Notifications Example") //set title of notification
//            .setContentText("This is a notification message") //this is notification message
//            .setAutoCancel(true) // makes auto cancel of notification
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification
//
//        val notificationIntent = Intent(this, NotificationView::class.java)
//        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////        //notification message will get at NotificationView
//        notificationIntent.putExtra("message", "This is a notification message")
//        val pendingIntent = PendingIntent.getActivity(
//            this, 0, notificationIntent,
//            PendingIntent.FLAG_IMMUTABLE
//        )
//        builder.setContentIntent(pendingIntent)
////
////        // Add as notification
//        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        manager.notify(10001, builder.build())
}