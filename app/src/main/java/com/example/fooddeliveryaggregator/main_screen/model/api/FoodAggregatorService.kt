package com.example.fooddeliveryaggregator.main_screen.model.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL: String = "http://142.93.175.83/api/v1.0"

interface FoodAggregatorService {
    //temp
    @GET("/address")
    fun shopList(
        @Query("search") productName: String,
        @Query("address") address: String
    ): Observable<List<String>>
}