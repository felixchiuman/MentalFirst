package com.felix.mentalfirst.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.felix.mentalfirst.R
import com.felix.mentalfirst.ui.onboarding.OnBoardingActivity

class SplashScreenActivity : AppCompatActivity() {

    private val sharedPref = "onBoardingScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val onBoardingScreen: SharedPreferences = this.getSharedPreferences(sharedPref, MODE_PRIVATE)
            val isFirstTime:Boolean = onBoardingScreen.getBoolean("firstTime", true)

            if (isFirstTime){
                val editor : SharedPreferences.Editor = onBoardingScreen.edit()
                editor.putBoolean("firstTime",false)
                editor.commit()

                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}