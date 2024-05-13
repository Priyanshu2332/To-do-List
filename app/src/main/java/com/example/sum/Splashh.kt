package com.example.sum

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Splashh : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splashh)

        supportActionBar?.hide()   //use for to hide actiona bar

        Handler(Looper.getMainLooper()).postDelayed(
            {val i= Intent(this,MainActivity::class.java)
                startActivity(i)},2500)
    }
}