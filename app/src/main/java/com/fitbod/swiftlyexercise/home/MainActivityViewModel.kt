package com.fitbod.swiftlyexercise.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitbod.swiftlyexercise.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val productRepository = ProductRepository()
    val productLiveData = productRepository.productLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getItems()
        }
    }

}