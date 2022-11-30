package com.joduma.ines.routes

import com.joduma.ines.models.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ClientRoutes {

    @GET("clients")
    fun index (): Call<ArrayList<Client>>

}