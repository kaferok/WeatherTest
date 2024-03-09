package com.veko.data.retorift

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.veko.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    internal fun build(): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(ApiKeyInterceptor())
            addInterceptor(getLoggingInterceptor())
        }
        return build(httpClient.build())
    }

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private fun buildMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun build(client: OkHttpClient) =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(buildMoshi()))
            .baseUrl(BuildConfig.SERVER_URL)
            .client(client)
            .build()

}