package com.joduma.ines.providers

import com.joduma.ines.api.ApiRoutes
import com.joduma.ines.models.Product
import com.joduma.ines.models.Provider
import com.joduma.ines.routes.ProductRoutes
import com.joduma.ines.routes.ProviderRoutes
import retrofit2.Call

class ProductsProvider {

    private var productRoutes: ProductRoutes? = null

    init {
        val api = ApiRoutes()
        productRoutes = api.getProductsRoutes();
    }

    fun index(): Call<ArrayList<Product>>? {
        return productRoutes?.index()
    }
}