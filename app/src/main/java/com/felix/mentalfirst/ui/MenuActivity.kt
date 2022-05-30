package com.felix.mentalfirst.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.felix.mentalfirst.databinding.ActivityMenuBinding


class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCall.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),1)
            }else{
                makePhoneCall()
            }
        }

        binding.btnChat.setOnClickListener {
            directToWa()
        }
    }

    private fun directToWa() {
        if (isWhatappInstalled()) {
            val i = Intent(
                Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + "628118436633" + "&text=" + "")
            )
            startActivity(i)
        } else {
            Toast.makeText(this, "Whatsapp is not installed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun makePhoneCall() {
        val number = "119"
        val call = Intent(Intent.ACTION_CALL)

        call.data = (Uri.parse("tel:" +number))
        startActivity(call)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1)makePhoneCall()
    }

    private fun isWhatappInstalled(): Boolean {
        val packageManager = packageManager
        val whatsappInstalled: Boolean
        whatsappInstalled = try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
        return whatsappInstalled
    }
}