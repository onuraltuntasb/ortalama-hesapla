package com.example.ortalamahesapla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash.*

class Splash_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        btnHesaplaSplash.setOnClickListener {
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
    }

}
