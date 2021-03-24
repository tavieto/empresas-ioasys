package br.com.tavieto.empresas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class LoginActivity : AppCompatActivity() {

    lateinit var button : AppCompatButton
    private var colorOption = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button = findViewById(R.id.btnLogin)
        button.setOnClickListener {
            homeActivity()
            //changeButtonColor()
        }
    }

    private fun homeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun changeButtonColor() {
        when(colorOption){
            0 -> {
                colorOption = 1
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.background_button_error))
            }
            else ->{
                colorOption = 0
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.background_button))
            }
        }
    }

}