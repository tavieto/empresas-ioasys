package br.com.tavieto.empresas.remote

import br.com.tavieto.empresas.Company
import br.com.tavieto.empresas.CompanyType

fun CompanyResponse.toModel() : Company {
    return Company(
        id = id,
        companyName = enterpriseName,
        pathImage = "https://empresas.ioasys.com.br$photo",
        description = description,
        country = country,
        companyType = enterpriseType.toModel()
    )
}

fun CompanyTypeResponse.toModel(): CompanyType {
    return CompanyType(
        companyTypeName = enterpriseTypeName
    )
}