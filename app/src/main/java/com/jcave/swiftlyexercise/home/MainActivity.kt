package com.jcave.swiftlyexercise.home

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
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


        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels

        productAdapter = ProductAdapter(emptyList(), width)
        val productLayoutManager = FlexboxLayoutManager(this).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
        }

        binding.recyclerview.apply {
            adapter = productAdapter
            layoutManager = productLayoutManager
        }

    }

    private fun updateView(productList: List<ItemResultsResponse.ManagerSpecial>) {
        productAdapter.update(productList)
    }

}