package br.com.tavieto.empresas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
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
                ))
        }
    }


}