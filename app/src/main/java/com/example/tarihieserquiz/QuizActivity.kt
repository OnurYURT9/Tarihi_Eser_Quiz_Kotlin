package com.example.tarihieserquiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogruSoru:Bayraklar
    private lateinit var tumSecenekler:HashSet<Bayraklar>
    private lateinit var vt:VeritabaniYardimcisi

    private var soruSayac = 0
    private var dogrusayac = 0
    protected var yanlissayac = 0









        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_quiz)
            vt = VeritabaniYardimcisi(this)









            buttonA.setOnClickListener {
                dogruKontrol(buttonA)
                sorusayackontrol()
                val sayici = object : CountDownTimer(10000, 1000) {
                    override fun onTick(i: Long) {
                        textviewsayici.text = "Kalan Süre:${i / 1000} sn"


                    }

                    override fun onFinish() {
                        soruSayac++
                        sorular = Bayraklardao().rastgele5bayrakgetir(vt)
                        soruYukle()
                        yanlissayac++
                        textViewyanlis.text = "Yanlış : $yanlissayac"

                    }
                }
                sayici.start()
            }
            buttonB.setOnClickListener {
                dogruKontrol(buttonB)
                sorusayackontrol()
                val sayici = object : CountDownTimer(10000, 1000) {
                    override fun onTick(i: Long) {
                        textviewsayici.text = "Kalan Süre:${i / 1000} sn"

                    }

                    override fun onFinish() {
                        soruSayac++
                        yanlissayac++
                        textViewyanlis.text = "Yanlış : $yanlissayac"
                        sorular = Bayraklardao().rastgele5bayrakgetir(vt)
                        soruYukle()
                        soruYukle()
                    }
                }
                sayici.start()
            }
            buttonC.setOnClickListener {
                dogruKontrol(buttonC)
                sorusayackontrol()
                val sayici = object : CountDownTimer(10000, 1000) {
                    override fun onTick(i: Long) {
                        textviewsayici.text = "Kalan Süre:${i / 1000} sn"

                    }

                    override fun onFinish() {
                        soruSayac++
                        yanlissayac++
                        textViewyanlis.text = "Yanlış : $yanlissayac"
                        sorular = Bayraklardao().rastgele5bayrakgetir(vt)
                        soruYukle()
                    }

                }
                sayici.start()
            }
            buttonD.setOnClickListener {
                dogruKontrol(buttonD)
                sorusayackontrol()
                val sayici = object : CountDownTimer(10000, 1000) {
                    override fun onTick(i: Long) {
                        textviewsayici.text = "Kalan Süre:${i / 1000} sn"

                    }


                    override fun onFinish() {
                        soruSayac++
                        yanlissayac++
                        textViewyanlis.text = "Yanlış : $yanlissayac"
                        sorular = Bayraklardao().rastgele5bayrakgetir(vt)
                        soruYukle()
                    }
                }
                sayici.start()
            }

        }


    fun soruYukle(){
        textViewsorusayi.text = "${soruSayac+1}.Soru"
        dogruSoru = sorular.get(soruSayac)
        imageViewbayrak.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))

        yanlisSecenekler = Bayraklardao().rastgele3yanlissecenekgetir(vt,dogruSoru.bayrak_id)
        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        buttonA.text = tumSecenekler.elementAt(0).bayrak_ad
        buttonB.text = tumSecenekler.elementAt(1).bayrak_ad
        buttonC.text = tumSecenekler.elementAt(2).bayrak_ad
        buttonD.text = tumSecenekler.elementAt(3).bayrak_ad

    }
    fun sorusayackontrol(){
        soruSayac++
        if(soruSayac != 10){
            soruYukle()
        }else{
            val intent =Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogrusayac)
            startActivity(intent)
            finish()
        }
    }
    fun dogruKontrol(button:Button){
        val buttonYazi = button.text.toString()
        val dogruCevap = dogruSoru.bayrak_ad

        if(buttonYazi == dogruCevap){

            dogrusayac++
        }else{

            yanlissayac++
    }
    textViewdogru.text = "Doğru : $dogrusayac"
        textViewyanlis.text = "Yanlış : $yanlissayac"
}
}