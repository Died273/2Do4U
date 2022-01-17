package com.example.activiteitenzoeker

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

val activityRecommendation: ArrayList<Int> = ArrayList()

class ActivityRecommendation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation)
        supportActionBar?.hide()
        //receives information about the activities
        val activityInformation = ActivityInformation.getInformation()
        val tvActivityName = findViewById<TextView>(R.id.tv_activity_name)
        val tvActivityIcon = findViewById<ImageView>(R.id.iv_activity_icon)
        val tvActivityDescription = findViewById<TextView>(R.id.tv_activity_description)
        val btnPreviousScreen = findViewById<ImageButton>(R.id.ib_previous_screen)
        val btnNextScreen = findViewById<ImageButton>(R.id.ib_next_screen)
        var activityRecommendationPosition = 0
        //receives information of the preferences of the user from preference screen activity
        val matchCheckActivity = intent.getIntegerArrayListExtra("checkList")
        //shows the recommended activity with information
        fun updateActivity(){
            if (activityRecommendation.size !=0) {
                tvActivityName.text =
                    activityInformation[activityRecommendation[activityRecommendationPosition]].name
                tvActivityIcon.setImageResource(activityInformation[activityRecommendation[activityRecommendationPosition]].icon)
                tvActivityDescription.text =
                    activityInformation[activityRecommendation[activityRecommendationPosition]].description
            }
            else{
                Toast.makeText(this, "No activity has been found", Toast.LENGTH_SHORT).show()
            }
        }

        if (matchCheckActivity != null) {
            activityRecommendation.clear()
            for (i in matchCheckActivity.indices) {
                //checks what activities are a match
                if (matchCheckActivity[i] == 5) {
                    activityRecommendation.add(i)
                }
            }
            updateActivity()
        }
        btnPreviousScreen.setOnClickListener {
            if (activityRecommendationPosition == 0) {
                //goes back to filter screen
                val intent = Intent(this, PreferenceScreenActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                //goes to previous shown activity
                activityRecommendationPosition -= 1
                updateActivity()
            }
        }
        btnNextScreen.setOnClickListener {
            //goes to next recommended activity with information
            if (compareValues(activityRecommendation.size - 1, activityRecommendationPosition) > 0) {
                activityRecommendationPosition += 1
                updateActivity()
            }
        }
    }
}
