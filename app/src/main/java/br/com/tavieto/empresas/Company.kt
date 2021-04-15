package br.com.tavieto.empresas

data class Company (
    val id: Int = 0,
    val companyName: String = "",
    val description: String = "",
    val pathImage: String? = "",
    val country: String = "",
    val companyType: CompanyType
)

data class CompanyType (
    val id: Int = 0,
    val companyTypeName: String = ""
)
