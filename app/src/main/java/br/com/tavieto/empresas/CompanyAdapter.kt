package br.com.tavieto.empresas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CompanyAdapter(private val callback : (Company) -> Unit) : RecyclerView.Adapter<CompanyAdapter.CompaniesViewHolder>(){

    private var companies: List<Company> = emptyList()

    inner class CompaniesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(company: Company) {
            itemView.findViewById<AppCompatTextView>(R.id.textCompanyName).text = company.companyName
            itemView.findViewById<AppCompatTextView>(R.id.textCompanyRole).text = company.companyType.companyTypeName
            itemView.findViewById<AppCompatTextView>(R.id.textCompanyCountry).text = company.country
            val imageView = itemView.findViewById<AppCompatImageView>(R.id.imageCompany)
            itemView.setOnClickListener {
                callback(company)
            }

            Glide
                .with(itemView)
                .load(company.pathImage)
                .centerCrop()
                .placeholder(R.drawable.img_logo)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompaniesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_company, parent, false)
        return CompaniesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompaniesViewHolder, position: Int) {
        holder.bind(companies[position])
    }

    override fun getItemCount(): Int {
        return companies.size
    }

    fun setItens(list: List<Company>) {
        companies = list
        notifyDataSetChanged()
    }
}