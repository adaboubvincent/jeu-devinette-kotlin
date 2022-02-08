package tg.adabounou_kodjovi_vincent.devin

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tg.adabounou_kodjovi_vincent.devin.viewmodel.PseudoNumberViewModel



class JeuActivity : AppCompatActivity() {
    private lateinit var myViewModel: PseudoNumberViewModel
    var numberIncrement: Int = 5
    var nomPseudo: String = ""
    lateinit var viewNumber: TextView
    lateinit var viewPseudo: TextView
    var randomNumber: Int = (0..100).random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jeu)

        viewNumber = findViewById<TextView>(R.id.txt_tentative)
        viewPseudo = findViewById<TextView>(R.id.txt_chance)
        myViewModel = ViewModelProvider(this).get(PseudoNumberViewModel::class.java)

        viewPseudo.text = "Bonne chance "+intent.getStringExtra("pseudo")
        viewNumber.text = "Il vous reste "+numberIncrement+" tentative(s)"

        findViewById<Button>(R.id.btn_valider).setOnClickListener {
            val n = intent.getStringExtra("pseudo")
            myViewModel.setPseudo(n!!)
            myViewModel.setRandomNimber(randomNumber)
            viewPseudo.text = intent.getStringExtra("pseudo").toString()
        }
        myViewModel.getPseudo().observe(this, Observer {
            nomPseudo = it
            viewPseudo.text =  "Bonne chance "+nomPseudo.toString()
        })


        myViewModel.getRandomNimber().observe(this, Observer {
            randomNumber = it
        })





        myViewModel.getNumber().observe(this, Observer {
            numberIncrement = it
            viewNumber.text = "Il vous reste "+numberIncrement.toString()+" tentative(s)"
        })
        findViewById<Button>(R.id.btn_valider).setOnClickListener {
            if(findViewById<EditText>(R.id.edt_number).text.toString().toInt() == randomNumber){
                dialogBox("BRAVO "+nomPseudo+" !!! Vous avez réussi")
            }else if(numberIncrement != 1){
                Toast.makeText(this, "Réessayer", Toast.LENGTH_LONG).show()
            }
            if(numberIncrement <= 1){
                dialogBox("Vous avez perdu!!!")
                Toast.makeText(this, "Le nombre qui a été choisi est "+randomNumber, Toast.LENGTH_LONG).show()
            }
            findViewById<EditText>(R.id.edt_number).setText("")
            val n = numberIncrement-1
            myViewModel.setNumber(n)
            viewNumber.text =  "Il vous reste "+numberIncrement.toString()+" tentative(s)"
        }

    }

    fun dialogBox(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setTitle("Jeu Devin")
        alertDialogBuilder.setMessage(message)

        alertDialogBuilder.setPositiveButton("RECOMMENCER"
        ) { arg0, arg1 ->
            randomNumber = (0..100).random()
            myViewModel.setRandomNimber(randomNumber)
            numberIncrement = 5
            myViewModel.setNumber(numberIncrement)
            viewNumber.text =  "Il vous reste "+numberIncrement.toString()+" tentative(s)"
        }
        alertDialogBuilder.setNegativeButton("Ok"
        ) { arg0, arg1 ->
            startActivity(Intent(this, MainActivity::class.java))
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}