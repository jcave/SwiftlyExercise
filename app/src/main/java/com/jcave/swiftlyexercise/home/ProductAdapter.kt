package com.jcave.swiftlyexercise.home

import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jcave.swiftlyexercise.R
import com.jcave.swiftlyexercise.models.Product
import com.jcave.swiftlyexercise.utils.Utils

class ProductAdapter(
    var products: List<Product> = emptyList(),
    var baseUnitWidth: Float = 0f,
    var baseUnitHeight: Float = 0f
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
            VIEW_PRODUCT_SMALL -> {
                view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_product_small, parent, false)
                holder = ProductHolder(view)
            }
            VIEW_PRODUCT_WIDE -> {
                view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_product_wide, parent, false)
                holder = ProductHolder(view)
            }
            else -> {
                view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_product, parent, false)
                holder = ProductHolder(view)
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_PRODUCT, VIEW_PRODUCT_SMALL, VIEW_PRODUCT_WIDE -> bindProduct(
                holder as ProductHolder,
                position
            )
            VIEW_HEADER -> bindHeader(holder as HeaderHolder)
        }
    }

    private fun bindHeader(holder: HeaderHolder) {
        if(products.isNotEmpty()) {
            holder.title.visibility = View.VISIBLE
        } else {
            holder.title.visibility = View.INVISIBLE
        }
    }

    private fun bindProduct(holder: ProductHolder, position: Int) {
        val product = products[position - 1]

        holder.apply {

            val width = (product.width.toFloat() * baseUnitWidth).toInt()
            val height = (product.height.toFloat() * baseUnitHeight).toInt()

            val formattedListPrice = Utils.formatCurrency(product.originalPrice)
            val spannedPrice = SpannableString(formattedListPrice)
            spannedPrice.setSpan(
                StrikethroughSpan(),
                0,
                formattedListPrice.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            price.text = spannedPrice
            salePrice.text = Utils.formatCurrency(product.price)
            title.text = product.displayName

            imgProduct.load(product.imageUrl) {
                crossfade(true)
            }

            layout.layoutParams.width = width
            layout.layoutParams.height = height
        }

    }

    override fun getItemViewType(position: Int): Int {

        if (position == 0) return VIEW_HEADER

        val item = products[position - 1]

        if (item.width <= 5) {
            return VIEW_PRODUCT_SMALL
        } else if (item.width > 10 && item.height < 6) {
            return VIEW_PRODUCT_WIDE
        }

        return VIEW_PRODUCT
    }

    override fun getItemCount(): Int {
        return products.count() + 1
    }

    fun update(
        productResults: List<Product>,
        baseUnitWidth: Float,
        baseUnitHeight: Float
    ) {

        this.baseUnitHeight = baseUnitHeight
        this.baseUnitWidth = baseUnitWidth

        val diffResult = DiffUtil.calculateDiff(
            ProductDiffCallback(products, productResults)
        )

        this.products = productResults
        diffResult.dispatchUpdatesTo(this)
    }

    class HeaderHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.text_title)
    }

    class ProductHolder(v: View) : RecyclerView.ViewHolder(v) {
        val price: TextView = v.findViewById(R.id.text_price)
        val salePrice: TextView = v.findViewById(R.id.text_sale_price)
        val title: TextView = v.findViewById(R.id.text_title)
        val imgProduct: ImageView = v.findViewById(R.id.image_item)
        val layout: FrameLayout = v.findViewById(R.id.layout_product)
    }

    companion object {
        private const val VIEW_HEADER = 0
        private const val VIEW_PRODUCT = 1
        private const val VIEW_PRODUCT_SMALL = 2
        private const val VIEW_PRODUCT_WIDE = 3
    }

}