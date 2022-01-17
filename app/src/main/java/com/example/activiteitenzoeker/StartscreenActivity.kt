package com.example.activiteitenzoeker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen_activity)
        supportActionBar?.hide()
        //from start screen to the preference screen
        val btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener{
            val intent = Intent(this, PreferenceScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}