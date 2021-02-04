package com.jcave.swiftlyexercise.models

data class ProductResultsResponse(
    val canvasUnit: Int = 0,
    val managerSpecials: List<Product> = listOf()
)