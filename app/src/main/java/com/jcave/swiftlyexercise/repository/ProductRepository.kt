package com.jcave.swiftlyexercise.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jcave.swiftlyexercise.api.ApiProvider
import com.jcave.swiftlyexercise.api.ApiService
import com.jcave.swiftlyexercise.models.ProductResultsResponse

class ProductRepository {

    private val apiService: ApiService = ApiProvider.createService(ApiService::class.java)
    val productLiveData = MutableLiveData<ProductResultsResponse>()

    suspend fun getItems() {

        val response = apiService.getItems()
        Log.i(TAG, "$response")

        if (response.isSuccessful) {
            productLiveData.postValue(response.body())
        }

    }

    companion object {
        val TAG = ProductRepository::class.java.simpleName
    }

}