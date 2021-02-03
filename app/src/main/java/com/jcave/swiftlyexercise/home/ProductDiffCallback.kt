package com.jcave.swiftlyexercise.home

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.jcave.swiftlyexercise.models.ProductResultsResponse

class ProductDiffCallback(
    private val oldList: List<ProductResultsResponse.ManagerSpecial>,
    private val newList: List<ProductResultsResponse.ManagerSpecial>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].displayName === newList[newItemPosition].displayName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (name, height, url, _) = oldList[oldItemPosition]
        val (name2, height2, url2, _) = newList[newItemPosition]

        return name == name2 && height == height2 && url == url2
    }
//
//    @Nullable
//    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
//        return super.getChangePayload(oldPosition, newPosition)
//    }
}