package com.jcave.swiftlyexercise.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jcave.swiftlyexercise.R
import com.jcave.swiftlyexercise.models.ItemResultsResponse

class ProductAdapter(var productList: List<ItemResultsResponse.ManagerSpecial>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindProduct(holder as ProductHolder, position)
    }

    private fun bindProduct(holder: ProductHolder, position: Int) {
        val product = productList[position]

        holder.apply {
            price.text = product.originalPrice.toString()
            salePrice.text = product.price.toString()
            title.text = product.displayName

            imgProduct.load(product.imageUrl) {
                crossfade(true)
            }

            imgProduct.setImageURI(Uri.parse(product.imageUrl))
        }
    }

    override fun getItemCount(): Int {
        return productList.count()
    }

    public fun update(productList: List<ItemResultsResponse.ManagerSpecial>){
        this.productList = productList
        notifyDataSetChanged()
    }

    class ProductHolder(v: View) : RecyclerView.ViewHolder(v) {
        val price: TextView = v.findViewById(R.id.text_price)
        val salePrice: TextView = v.findViewById(R.id.text_sale_price)
        val title: TextView = v.findViewById(R.id.text_title)
        val imgProduct: ImageView = v.findViewById(R.id.image_item)
    }

}