package com.jcave.swiftlyexercise.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("display_name")
    val displayName: String = "",
    val height: Int = 0,
    val imageUrl: String = "",
    @SerializedName("original_price")
    val originalPrice: Float = 0.0f,
    val price: Float = 0.0f,
    val width: Int = 0
) {

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }

        other as Product

        if (displayName != other.displayName) return false
        if (height != other.height) return false
        if (imageUrl != other.imageUrl) return false
        if (originalPrice != other.originalPrice) return false
        if (price != other.price) return false
        if (width != other.width) return false

        return true
    }

}
