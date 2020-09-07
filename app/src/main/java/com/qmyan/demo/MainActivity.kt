package com.qmyan.demo

import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.qmyan.demo.contentproviderpractice.ContentProviderDemoActivity
import com.qmyan.demo.databasepractice.DataBaseDemoActivity
import com.qmyan.demo.uipractice.SMSDemoActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
//        val telm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

//        Log.e(TAG, "DeviceId: ${telm.deviceId}")
//        Log.e(TAG, "SubscriberId: ${telm.subscriberId}")
//        Toast.makeText(this , "DeviceId: ${telm.deviceId} \n SubscriberId: ${telm.subscriberId}", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        // SMSDemoActivity.actionStart(this)
        // DataBaseDemoActivity.actionStart(this)
        ContentProviderDemoActivity.actionStart(this)
    }
}