package com.joduma.ines.routes

import com.joduma.ines.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ClientRoutes {

    @Multipart
    @POST("/clients")
    fun create (
        @Part("client") client: RequestBody
    ): Call<ResponseHttp>

}