package com.example.fooddeliveryaggregator.search.model.api

import com.example.fooddeliveryaggregator.search.model.SuggestionRequestBody
import com.example.fooddeliveryaggregator.search.model.SuggestionResponseList
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


const val BASE_URL: String = "https://suggestions.dadata.ru/"

fun createAddressSuggestionService(): AddressSuggestionService {


    val logging = HttpLoggingInterceptor()
    // set your desired log level
    // set your desired log level
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    val httpClient = OkHttpClient.Builder()
    // add your other interceptors …

    // add logging as last interceptor
    // add your other interceptors …

    // add logging as last interceptor
    httpClient.addInterceptor(logging)

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(httpClient.build())
        .build()

    return retrofit.create(AddressSuggestionService::class.java)
}

interface AddressSuggestionService {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Authorization: Token da776ec3a1d6c50ec4838de1ff85ea5b735c8b7e"
    )
    @POST("suggestions/api/4_1/rs/suggest/address")
    fun getSuggestionsList(
        @Body suggestionRequestBody: SuggestionRequestBody
    ): Single<SuggestionResponseList>
}