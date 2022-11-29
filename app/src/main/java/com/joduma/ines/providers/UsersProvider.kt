package com.joduma.ines.providers


import com.joduma.ines.api.ApiRoutes
import com.joduma.ines.models.*
import com.joduma.ines.routes.UsersRoutes
import retrofit2.Call

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