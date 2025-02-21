package com.helloworld.firebase


import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirebaseMessageService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.v("CloudMessage","Cloud Message From: ${message.from}")

        if (message.data.isNotEmpty()) {
            Log.v("CloudMessage", "Cloud Message is ${message.data}")
        }

        Log.v("CloudMessage","Message Notification Body ${message.data["body"]}")

        if (message.notification != null) {
            Log.v("CloudMessage","Cloud Message Notification is ${message.notification}")
            Log.v("CloudMessage","Cloud Message Notification Title is ${message.notification!!.title}")
            Log.v("CloudMessage","Cloud Message Notification Body is ${message.notification!!.body}")
        }
    }

    override fun onNewToken(token: String) {
        Log.d("onNewToken",token)
        super.onNewToken(token)
        GlobalScope.launch {
            saveGCMToken(token)
        }
    }

    private suspend fun saveGCMToken(token: String) {
//        val gcmTokenKey = stringPreferencesKey("gmc_token")
//        baseContext.dataStore.edit { it[gcmTokenKey] = token }
    }
}