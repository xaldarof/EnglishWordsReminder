package xaldarof.dictionary.english.service

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xaldarof.dictionary.english.data.AppDatabase

class ActivityReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1?.getStringExtra("seen") == "seen") {
            if (p0 != null) {
                val notificationManager =
                    p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancel(123)
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase.getDatabase(p0).getWordsDao().clearUnSeenWords()
                }
            }
        }
    }
}