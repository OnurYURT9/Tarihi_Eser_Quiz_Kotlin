package com.example.tarihieserquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val dogruSayac = intent.getIntExtra("dogruSayac",0)
        textViewsonuc.text = "$dogruSayac Doğru ${10-dogruSayac} Yanlış"
        yuzdeSonuc.text = "% ${dogruSayac*100/10} Başarı"
        buttontekrar.setOnClickListener{
            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()
        }
    }
}