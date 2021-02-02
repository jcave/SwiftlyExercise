package com.jcave.swiftlyexercise.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jcave.swiftlyexercise.databinding.ActivityMainBinding
import com.jcave.swiftlyexercise.models.ItemResultsResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.productLiveData.observe(this, {
            updateView(it.managerSpecials)
        })

        productAdapter = ProductAdapter(emptyList())
        val productLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        binding.recyclerview.apply {
            adapter = productAdapter
            layoutManager = productLayoutManager
        }

    }

    private fun updateView(productList: List<ItemResultsResponse.ManagerSpecial>) {
        productAdapter.update(productList)
    }
}