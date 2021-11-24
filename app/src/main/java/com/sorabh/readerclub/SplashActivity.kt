package com.sorabh.readerclub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import com.sorabh.readerclub.activities.MainActivity

class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
     Handler().postDelayed({
       val intent = Intent(this,MainActivity::class.java)
         startActivity(intent)
         finish()
     },2000)
    }


}