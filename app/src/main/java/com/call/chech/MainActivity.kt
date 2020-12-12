package com.call.chech

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity: AppCompatActivity() {

    private lateinit var textStatus: TextView
    private lateinit var textPhone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textStatus = findViewById(R.id.tvForCall)
        textPhone = findViewById(R.id.tvPhone)
        checkPermission()
        initListener()
    }

    private fun initListener() {
        textStatus.text = PhoneListener.currentState.name
        PhoneListener.stateListener = object: IPhoneState {
            override fun onChangePhoneState(state: EPhoneState, date: Long, phone: String?) {
                textStatus.text = state.name
                textPhone.text = phone
            }
        }
    }

    private fun checkPermission() {
        if (isGranted(Manifest.permission.READ_PHONE_STATE).not()
            || isGranted(Manifest.permission.READ_CALL_LOG)) {
            val permissions = arrayOf(
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_PHONE_STATE
            )
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }

    private fun isGranted(type: String): Boolean {
        return ContextCompat.checkSelfPermission(this, type) ==
                PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}