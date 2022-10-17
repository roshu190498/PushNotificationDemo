package com.example.pnd

import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }


    override fun onStart() {
        super.onStart()
        btnSendNotify.setOnClickListener {
            Toast.makeText(this,"test",Toast.LENGTH_SHORT).show()
            addNotification()
        }
    }

    private fun addNotification() {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.btn_default) //set icon for notification
            .setContentTitle("Notifications Example") //set title of notification
            .setContentText("This is a notification message") //this is notification message
            .setAutoCancel(true) // makes auto cancel of notification
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification
        val notificationIntent = Intent(this, NotificationView::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a notification message")
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)

        // Add as notification
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }

}