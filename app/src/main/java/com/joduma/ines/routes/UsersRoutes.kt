package com.joduma.ines.routes

import com.joduma.ines.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UsersRoutes {
    @POST( "users" )
    fun  register(@Body user: User): Call<ResponseHttp>

    @POST( "/providers" )
    fun  provider(@Body provider: Provider): Call<ResponseHttp>

    @POST( "/api/products" )
    fun  product(@Body product: Product): Call<ResponseHttp>

    @POST( "/clients" )
    fun  client(@Body client: Client): Call<ResponseHttp>


    @FormUrlEncoded
    @POST( "login" )
    fun login(@Field("username") username: String, @Field("password") password: String): Call<ResponseHttp>

    @Multipart
    @PUT("users/update")
    fun update (
        @Part image: MultipartBody.Part,
        @Part("user") user: RequestBody

    ): Call<ResponseHttp>

}