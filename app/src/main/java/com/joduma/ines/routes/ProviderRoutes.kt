package com.joduma.ines.routes

import com.joduma.ines.models.*
import retrofit2.Call
import retrofit2.http.*


interface ProviderRoutes {

    @GET("providers")
    fun index (): Call<ArrayList<Provider>>
}