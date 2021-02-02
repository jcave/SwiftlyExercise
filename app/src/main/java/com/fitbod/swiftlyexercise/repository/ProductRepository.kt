package com.fitbod.swiftlyexercise.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fitbod.swiftlyexercise.api.ApiProvider
import com.fitbod.swiftlyexercise.api.ApiService
import com.fitbod.swiftlyexercise.models.ItemResultsResponse

class ProductRepository {

    private val apiService: ApiService = ApiProvider.createService(ApiService::class.java)
    val productLiveData = MutableLiveData<ItemResultsResponse>()

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