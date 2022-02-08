package tg.adabounou_kodjovi_vincent.devin

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Devin"

        findViewById<Button>(R.id.btn_commencer).setOnClickListener{
            openDialog()
        }
    }

    fun openDialog() {
        val dialog = Dialog(this) // Context, this, etc.
        dialog.setContentView(R.layout.dialog_pseudo)
        dialog.setTitle("Jeu Devin")
        dialog.findViewById<Button>(R.id.dialog_cancel).setOnClickListener{
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.dialog_ok).setOnClickListener{
            val intJeu = Intent(this, JeuActivity::class.java)
            intJeu.putExtra("pseudo", dialog.findViewById<EditText>(R.id.edt_pseudo).text.toString())
            startActivity(intJeu)
        }

        dialog.show()
    }

}