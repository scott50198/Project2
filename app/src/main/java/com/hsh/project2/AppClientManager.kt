package com.hsh.project2

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppClientManager private constructor() {
    private val retrofit: Retrofit
    private val token = "token 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"

    init {
        val httpClient = OkHttpClient.Builder()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor { chain ->
            val rq = chain.request()
            rq.newBuilder()
                .addHeader("Authorization", token)
                .build()
            chain.proceed(rq)
        }



        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    companion object {
        private val manager = AppClientManager()
        val client: Retrofit
            get() = manager.retrofit
    }
}
