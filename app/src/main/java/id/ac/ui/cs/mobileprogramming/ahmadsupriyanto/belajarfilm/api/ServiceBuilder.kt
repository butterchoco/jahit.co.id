package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.belajarfilm.api

import okhttp3.Dns
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private val client = OkHttpClient.Builder().dns(Dns.SYSTEM)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/") // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}