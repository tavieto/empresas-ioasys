package br.com.tavieto.empresas

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class LoginActivity : AppCompatActivity() {

    lateinit var button : AppCompatButton
    var colorOption = 0

    val COLOR_button_background = "#57BBBC"
    val COLOR_button_background_error = "#748383"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button = findViewById(R.id.btnLogin)
        button.setOnClickListener {

            when(colorOption){
                0 -> {
                    colorOption = 1
                    button.setBackgroundColor(Color.parseColor(COLOR_button_background_error))
                }
                else ->{
                    colorOption = 0
                    button.setBackgroundColor(Color.parseColor(COLOR_button_background))
                }
            }
        }
    }
}