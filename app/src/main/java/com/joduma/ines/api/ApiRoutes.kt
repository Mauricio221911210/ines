package com.joduma.ines.api

import com.joduma.ines.routes.ClientRoutes
import com.joduma.ines.routes.UsersRoutes


class ApiRoutes {
    val API_URL = "https://www.slyventshop.com/api/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes(): UsersRoutes {
        return retrofit.getCliente(API_URL).create(UsersRoutes::class.java)
    }

    fun getClientsRoutes(): ClientRoutes {
        return retrofit.getCliente(API_URL).create(ClientRoutes::class.java)
    }
}