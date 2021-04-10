package hu.itatti.calllist

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import hu.itatti.calllist.data.AppDatabase
import hu.itatti.calllist.data.Call
import java.util.*
import kotlin.concurrent.thread

class CallReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent){
            val outNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)

        thread {
            AppDatabase.getInstance(context).callDao().addCall(
                Call(
                null,
                outNum.toString(),
                Date(System.currentTimeMillis()).toString()
                )
            )
        }

            Toast.makeText(context,outNum, Toast.LENGTH_LONG).show()

        }




}