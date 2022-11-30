package com.joduma.ines.fragments.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.joduma.ines.R
import com.joduma.ines.adapters.ProductsAdapter
import com.joduma.ines.models.Product
import com.joduma.ines.models.User
import com.joduma.ines.providers.ProductsProvider
import com.joduma.ines.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsFragment : Fragment() {

    val TAG = "ProductsFragment"
    var myView: View? = null
    var recyclerViewProducts: RecyclerView? = null
    var productsProvider: ProductsProvider? = null
    var adapter: ProductsAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var products = ArrayList<Product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_products, container, false)

        recyclerViewProducts = myView?.findViewById(R.id.recyclerview_products)
        recyclerViewProducts?.layoutManager = LinearLayoutManager(requireContext())
        sharedPref = SharedPref(requireActivity())

        getUserFromSession()

        productsProvider = ProductsProvider()

        getProducts()
        return myView
    }

    private fun getProducts(){
        productsProvider?.index()?.enqueue(object: Callback<ArrayList<Product>>{
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                if(response.body() != null){
                    products = response.body()!!
                    adapter = ProductsAdapter(requireActivity(), products)
                    recyclerViewProducts?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Log.d(TAG, "Error: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }
    }
}