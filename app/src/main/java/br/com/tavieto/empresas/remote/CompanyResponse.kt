package br.com.tavieto.empresas.remote

import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("enterprise_name")
    val enterpriseName: String,

    @SerializedName("photo")
    val photo: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("city")
    val city: String? = null,

    @SerializedName("country")
    val country: String,

    @SerializedName("enterprise_type")
    val enterpriseType: CompanyTypeResponse
)