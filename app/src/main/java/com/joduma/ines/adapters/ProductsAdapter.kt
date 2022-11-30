package com.joduma.ines.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joduma.ines.R
import com.joduma.ines.activities.admin.home.AdminHomeActivity
import com.joduma.ines.models.Product
import com.joduma.ines.models.Provider
import com.joduma.ines.utils.SharedPref

class ProductsAdapter(val context: Activity, val products: ArrayList<Product>): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    val sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_product, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int{
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int){
        val product = products[position]

        holder.textViewProductCode.text = product.description
        holder.textViewProductPrice.text = "$${product.price}"
        holder.textViewProductDescription.text = "Codigo: ${product.code}"

//        holder.itemView.setOnClickListener { goToClient(product) }
    }

    fun goToClient(product: Provider){
        val i = Intent(context, AdminHomeActivity::class.java)
        context.startActivity(i)
    }

    class ProductsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewProductCode: TextView
        val textViewProductPrice: TextView
        val textViewProductDescription: TextView

        init {
            textViewProductCode = view.findViewById(R.id.tv_product_name)
            textViewProductPrice = view.findViewById(R.id.tv_product_price)
            textViewProductDescription = view.findViewById(R.id.tv_product_description)
        }
    }

}