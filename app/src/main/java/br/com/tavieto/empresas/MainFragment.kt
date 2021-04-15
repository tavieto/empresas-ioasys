package br.com.tavieto.empresas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import br.com.tavieto.empresas.remote.CompanyService
import br.com.tavieto.empresas.remote.GetCompaniesResponse
import br.com.tavieto.empresas.remote.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragment : Fragment() {

    private val args: MainFragmentArgs by navArgs()

    private val adapter by lazy { CompanyAdapter { clickItem(it) } }
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.navToolbar)
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.adapter = adapter

        getCompanies()
    }

    private fun getCompanies() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = CompanyService.newInstance().getEnterprises(
                accessToken = args.accessToken,
                client = args.client,
                uid = args.uid
            )

            handleResponse(response)
        }
    }

    private fun handleResponse(response: Response<GetCompaniesResponse>) {
        if (response.isSuccessful) {
            adapter.setItens(response.body()?.companies?.map { it.toModel() } ?: listOf())
        }
    }

    private fun clickItem(company: Company) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailsFragment(
                name = company.companyName,
                description = company.description,
                pathImage = company.pathImage
            )
        )
    }

}