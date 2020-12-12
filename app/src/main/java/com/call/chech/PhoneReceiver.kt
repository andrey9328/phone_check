package com.call.chech

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager

class PhoneReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null || intent.action == Intent.ACTION_BOOT_COMPLETED) return
        val manager = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        manager.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE)
    }
}