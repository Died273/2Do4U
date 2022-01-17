package com.example.activiteitenzoeker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

//list for the filters
var answers = mutableListOf("Answer0","Answer1","Answer2","Answer3","Answer4")
//list for comparison while using the searchActivity function
var emptyAnswers = mutableListOf("Answer0","Answer1","Answer2","Answer3","Answer4")
var matchCheckActivity: ArrayList<Int> = ArrayList()
var activityMatch = 0
const val resetValueActivityMatch = 0

class PreferenceScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference_screen)
        supportActionBar?.hide()
        //receive list of activities from database
        val activityList = DatabaseActivities.getActivity()
        val btnBinnen = findViewById<Button>(R.id.btn_Binnen)
        val btnBuiten = findViewById<Button>(R.id.btn_Buiten)
        val btnOchtend = findViewById<Button>(R.id.btn_Ochtend)
        val btnMiddag = findViewById<Button>(R.id.btn_Middag)
        val btnAvond = findViewById<Button>(R.id.btn_Avond)
        val btnPrijs0 = findViewById<Button>(R.id.btn_prijs0)
        val btnPrijs1 = findViewById<Button>(R.id.btn_prijs1)
        val btnPrijs2 = findViewById<Button>(R.id.btn_prijs2)
        val btnPrijs3 = findViewById<Button>(R.id.btn_prijs3)
        val btnPrijs4 = findViewById<Button>(R.id.btn_prijs4)
        val btnActief = findViewById<Button>(R.id.btn_actief)
        val btnPassief = findViewById<Button>(R.id.btn_passief)
        val btnOnline = findViewById<Button>(R.id.btn_online)
        val btnOffline = findViewById<Button>(R.id.btn_offline)
        val btnSearch = findViewById<Button>(R.id.btn_search)
        val btnReset = findViewById<ImageButton>(R.id.ib_reset_button)

        btnReset.setOnClickListener {
            for (i in answers.indices) {
                answers[i] = emptyAnswers[i]
            }
        }
        //filter 1
        btnBinnen.setOnClickListener {
            answers[0] = "binnen"
        }
        btnBuiten.setOnClickListener {
            answers[0]="buiten"
        }
        //filter 2
        btnOchtend.setOnClickListener {
            answers[1] = "ochtend"
        }
        btnMiddag.setOnClickListener {
            answers[1]="middag"
        }
        btnAvond.setOnClickListener {
            answers[1] = "avond"
        }
        //filter 3
        btnPrijs0.setOnClickListener {
            answers[2] = "gratis"
        }
        btnPrijs1.setOnClickListener {
            answers[2] = "€"
        }
        btnPrijs2.setOnClickListener {
            answers[2] = "€€"
        }
        btnPrijs3.setOnClickListener {
            answers[2] = "€€€"
        }
        btnPrijs4.setOnClickListener {
            answers[2] = "€€€€"
        }
        //filter 4
        btnActief.setOnClickListener {
            answers[3] = "actief"
        }
        btnPassief.setOnClickListener {
            answers[3] = "passief"
        }
        //filter 5
        btnOnline.setOnClickListener {
            answers[4] = "online"
        }
        btnOffline.setOnClickListener {
            answers[4] = "offline"
        }

        fun searchActivity(){
            if (matchCheckActivity.size !=0) {
                matchCheckActivity.clear()
            }
            for (j in activityList.indices) {
                val activity: Activity = activityList[j]
                //compares activity with given answers
                for (i in answers.indices) {
                    when{
                        answers[i] == emptyAnswers[i] -> {
                            activityMatch += 1
                        }
                        answers[i] == activity.place -> {
                            activityMatch += 1
                        }
                        answers[i] == activity.cost -> {
                            activityMatch += 1
                        }
                        answers[i] == activity.moment -> {
                            activityMatch += 1
                        }
                        answers[i] == activity.state -> {
                            activityMatch += 1
                        }
                        answers[i] == activity.type -> {
                            activityMatch += 1
                        }
                        activity.place == "altijd" -> {
                            if (i==0) {
                                activityMatch += 1
                            }
                        }
                        activity.moment == "altijd" -> {
                            if (i==1) {
                                activityMatch += 1
                            }
                        }
                        activity.cost == "altijd" -> {
                            if (i==2) {
                                activityMatch += 1
                            }
                        }
                        activity.type == "altijd" -> {
                            if (i==3) {
                                activityMatch += 1
                            }
                        }
                        activity.state == "altijd" -> {
                            if (i==4) {
                                activityMatch += 1
                            }
                        }
                    }
                }
                matchCheckActivity.add(activityMatch)
                //resets value for new activity
                activityMatch= resetValueActivityMatch
            }
        }
        btnSearch.setOnClickListener {
            searchActivity()
            matchCheckActivity.size !=0
                val intent = Intent(this, ActivityRecommendation::class.java)
                //transfers the list of answers to other screen
                intent.putExtra("checkList", matchCheckActivity)
                //from preference screen to activity recommendation screen
                startActivity(intent)
                finish()
        }
    }
}