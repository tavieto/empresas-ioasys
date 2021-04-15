package br.com.tavieto.empresas.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface CompanyService {

    @POST("users/auth/sign_in")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<Unit>

    @GET("enterprises")
    suspend fun getEnterprises(
        @Header("access-token") accessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String
    ): Response<GetCompaniesResponse>

    companion object {
        fun newInstance(): CompanyService = Retrofit.Builder()
            .baseUrl("https://empresas.ioasys.com.br/api/v1/")
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CompanyService::class.java)

        private fun getClient(): OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }
}