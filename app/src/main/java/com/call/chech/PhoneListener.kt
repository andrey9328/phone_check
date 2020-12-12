package com.call.chech

import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import java.util.*

object PhoneListener: PhoneStateListener() {
    var stateListener: IPhoneState? = null
    var currentState: EPhoneState = EPhoneState.IDLE
    private var lastState: Int = TelephonyManager.CALL_STATE_IDLE
    private var isIncomingCall = false

    override fun onCallStateChanged(state: Int, phoneNumber: String?) {
        super.onCallStateChanged(state, phoneNumber)
        if (lastState == state) return

        when(state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                sendEvent(EPhoneState.INCOMING_CALL, phoneNumber)
                isIncomingCall = true
            }
            TelephonyManager.CALL_STATE_IDLE -> {
                checkIdleCall(phoneNumber)
            }
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                checkOutCall(phoneNumber)
            }
        }
        lastState = state
    }

    private fun checkOutCall(phone: String?) {
        if (lastState != TelephonyManager.CALL_STATE_RINGING) {
            sendEvent(EPhoneState.OUT_CALL, phone)
            isIncomingCall = false
        }

    }

    private fun checkIdleCall(phone: String?) {
        if (lastState == TelephonyManager.CALL_STATE_RINGING)
            sendEvent(EPhoneState.MISS_CALL, phone)
        else if(isIncomingCall) {
            sendEvent(EPhoneState.INCOMING_CALL_END, phone)
        } else {
            sendEvent(EPhoneState.OUT_CALL_END, phone)
        }
    }

    private fun sendEvent(type: EPhoneState, phone: String?) {
        val date = Date().time / 1000
        stateListener?.onChangePhoneState(type, date, phone)

    }

}