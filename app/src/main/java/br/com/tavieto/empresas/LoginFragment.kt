package br.com.tavieto.empresas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.tavieto.empresas.remote.CompanyService
import br.com.tavieto.empresas.remote.LoginRequest
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginFragment : Fragment() {

    lateinit var button: AppCompatButton
    lateinit var edtEmail: AppCompatEditText
    lateinit var edtPassword: TextInputLayout
    lateinit var progressBar: ProgressBar
    lateinit var bcProgressBar: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.btnLogin)
        edtEmail = view.findViewById(R.id.edtEmail)
        edtPassword = view.findViewById(R.id.edtPassword)
        progressBar = view.findViewById(R.id.progressBar)
        bcProgressBar = view.findViewById(R.id.bcProgressBar)

        button.setOnClickListener {
            loading(true)
            errorMessage(false)

            CoroutineScope(Dispatchers.Main).launch {
                val response = CompanyService.newInstance().login(
                    LoginRequest(
                        email = edtEmail.text.toString(),
                        password = edtPassword.editText?.text.toString()
                    )
                )

                handleLogin(response)
            }
        }
    }

    private fun handleLogin(response: Response<Unit>) {
        if (response.isSuccessful) {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToMainActivity(
                    accessToken = response.headers()["access-token"] ?: "",
                    client = response.headers()["client"] ?: "",
                    uid = response.headers()["uid"] ?: ""
                )
            )
            errorMessage(false)
            loading(false)
        } else {
            errorMessage(true)
            loading(false)
        }


    }

    private fun errorMessage(option: Boolean) {
        val errorText = if (option) resources.getString(R.string.login_error) else null

        if(option) {
            edtPassword.isErrorEnabled = true
            edtPassword.error = errorText
            edtPassword.errorContentDescription = errorText
            edtPassword.errorIconDrawable = resources.getDrawable(R.drawable.material_ic_error)

            edtEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_email, 0, R.drawable.material_ic_error, 0)

        } else {
            edtPassword.isErrorEnabled = false
            edtPassword.error = errorText
            edtPassword.errorContentDescription = errorText
            edtPassword.errorIconDrawable = null

            edtEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_email, 0, 0, 0)
        }
    }

    private fun loading(option: Boolean) {
        val visibility = setVisibility(option)

        progressBar.visibility = visibility
        bcProgressBar.visibility = visibility
    }

    private fun setVisibility(option: Boolean) = if (option) View.VISIBLE else View.GONE

}