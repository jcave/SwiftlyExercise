package com.jcave.swiftlyexercise.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jcave.swiftlyexercise.R
import com.jcave.swiftlyexercise.models.ItemResultsResponse

class ProductAdapter(
    var productList: List<ItemResultsResponse.ManagerSpecial>,
    var widthInPx: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val holder: RecyclerView.ViewHolder

        when (viewType) {
            VIEW_HEADER -> {
                view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_specials_header, parent, false)
                holder = HeaderHolder(view)
            }
            else -> {
                view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_sample, parent, false)
                holder = ProductHolder(view)
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_PRODUCT -> bindProduct(holder as ProductHolder, position)
        }
    }

    private fun bindProduct(holder: ProductHolder, position: Int) {
        val product = productList[position - 1]

        holder.apply {

            val baseUnitWidth = (widthInPx / 16.0)
            val baseUnitHeight = (widthInPx / 16.0)
            val width = (product.width.toFloat() * baseUnitWidth).toInt()
            val height = (product.height.toFloat() * baseUnitHeight).toInt()

            Log.i("OUTPUT", "${product.width} :: ${product.height} :: ${product.displayName}")
            Log.i(
                "OUTPUT",
                "${baseUnitWidth.toInt()} :: ${baseUnitHeight.toInt()} :: $width :: $height"
            )
            Log.i("OUTPUT", "---------")
//            price.text = "$baseUnitWidth :: $baseUnitHeight :: $width :: $height"
//            salePrice.text = product.price.toString()
            title.text = product.displayName
//            imgProduct.load(product.imageUrl) {
//                crossfade(true)
//            }


            layout.layoutParams.width = width
            layout.layoutParams.height = height
        }


    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_HEADER
            else -> VIEW_PRODUCT
        }
    }

    override fun getItemCount(): Int {
        return productList.count() + 1
    }

    fun update(productList: List<ItemResultsResponse.ManagerSpecial>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    class HeaderHolder(v: View) : RecyclerView.ViewHolder(v)

    class ProductHolder(v: View) : RecyclerView.ViewHolder(v) {
        //        val price: TextView = v.findViewById(R.id.text_price)
//        val salePrice: TextView = v.findViewById(R.id.text_sale_price)
        val title: TextView = v.findViewById(R.id.text_title)

        //        val imgProduct: ImageView = v.findViewById(R.id.image_item)
        val layout: FrameLayout = v.findViewById(R.id.layout_sample)
    }


    companion object {
        private const val VIEW_HEADER = 0
        private const val VIEW_PRODUCT = 1
    }

}