package com.example.callapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by ArshadKhan.......
 */

class MainActivity : AppCompatActivity() {
    private var AcceptCall: Boolean = false;
    companion object {
        private const val REQUEST_CODE: Int = 200;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.call_button)
        val button = findViewById<Button>(R.id.call_button)
        button.setOnClickListener {
            makeCall()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            AcceptCall = grantResults[0] == PackageManager.PERMISSION_GRANTED
            if (AcceptCall) {
                Toast.makeText(this, "permissions ok", Toast.LENGTH_SHORT).show()
                makeCall();
            } else {
                Toast.makeText(this, "permission required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makeCall() {
        val intent: Intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:8148097581")
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                REQUEST_CODE
            )
        } else {
            startActivity(intent)
        }

    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }


}



