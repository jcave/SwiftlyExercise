package com.jcave.swiftlyexercise.home

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.jcave.swiftlyexercise.databinding.ActivityMainBinding
import com.jcave.swiftlyexercise.models.ProductResultsResponse


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
            updateView(it)
        })

        productAdapter = ProductAdapter()
        val productLayoutManager = FlexboxLayoutManager(this).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
        }

        binding.recyclerview.apply {
            adapter = productAdapter
            layoutManager = productLayoutManager
        }

    }

    private fun updateView(productResponse: ProductResultsResponse) {

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val widthOfDisplay = metrics.widthPixels

        val canvasUnit = productResponse.canvasUnit.toFloat()
        val baseUnitWidth = (widthOfDisplay / canvasUnit)
        val baseUnitHeight = (widthOfDisplay / canvasUnit)

        productAdapter.update(productResponse.managerSpecials, baseUnitWidth, baseUnitHeight)
    }

}