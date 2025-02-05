package com.jcave.swiftlyexercise.api

import com.jcave.swiftlyexercise.models.ProductResultsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/"
    }

    @GET("Swiftly-Systems/code-exercise-android/master/backup")
    suspend fun getItems(): Response<ProductResultsResponse?>

}