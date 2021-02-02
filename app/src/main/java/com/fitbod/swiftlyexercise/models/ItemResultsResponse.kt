package com.fitbod.swiftlyexercise.models

import com.google.gson.annotations.SerializedName

data class ItemResultsResponse(
    val canvasUnit: Int = 0,
    val managerSpecials: List<ManagerSpecial> = listOf()
) {
    data class ManagerSpecial(
        @SerializedName("display_name")
        val displayName: String = "",
        val height: Int = 0,
        val imageUrl: String = "",
        @SerializedName("original_price")
        val originalPrice: Float = 0.0f,
        val price: Float = 0.0f,
        val width: Int = 0
    )
}