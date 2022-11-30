package com.joduma.ines.routes

import com.joduma.ines.models.*
import retrofit2.Call
import retrofit2.http.*

interface ProductRoutes {
    @GET("products")
    fun index (): Call<ArrayList<Product>>
}