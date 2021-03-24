package br.com.tavieto.empresas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class LoginActivity : AppCompatActivity() {

    lateinit var button : AppCompatButton
    lateinit var progress : ProgressBar
    lateinit var bcProgress : ConstraintLayout
    private var colorOption = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button = findViewById(R.id.btnLogin)
        button.setOnClickListener {
            //homeActivity()
            //changeButtonColor()
            loading()
        }
    }

    private fun homeActivity() {
        stopLoading()

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

    private fun stopLoading() {
        progress = findViewById(R.id.progressBar)
        bcProgress = findViewById(R.id.bcProgressBar)

        progress.visibility = View.GONE
        bcProgress.visibility = View.GONE
    }

    private fun loading() {
        progress = findViewById(R.id.progressBar)
        bcProgress = findViewById(R.id.bcProgressBar)

        progress.visibility = View.VISIBLE
        bcProgress.visibility = View.VISIBLE

        homeActivity()
    }
}