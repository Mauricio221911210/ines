package com.joduma.ines.providers


import com.google.android.gms.common.api.Api
import com.joduma.ines.api.ApiRoutes
import com.joduma.ines.models.*
import com.joduma.ines.routes.UsersRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class UsersProvider {
    private var usersRoutes: UsersRoutes? = null




    init {
        val  api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }

    fun register(user: User): Call<ResponseHttp>? {
        return usersRoutes?.register(user)
    }

    fun product(product: Product): Call<ResponseHttp>? {
        return usersRoutes?.product(product)
    }

    fun provider(provider: Provider): Call<ResponseHttp>? {
        return usersRoutes?.provider(provider )
    }

    fun client(client: Client): Call<ResponseHttp>? {
        return usersRoutes?.client(client)
    }

    fun login(username: String, password: String): Call<ResponseHttp>? {
        return usersRoutes?.login(username, password)
    }







}