package com.joduma.ines.api

import com.joduma.ines.routes.ClientRoutes
import com.joduma.ines.routes.ProductRoutes
import com.joduma.ines.routes.ProviderRoutes
import com.joduma.ines.routes.UsersRoutes

class ApiRoutes {
    val API_URL = "https://www.slyventshop.com/api/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

    fun getClientsRoutes(): ClientRoutes {
        return retrofit.getClient(API_URL).create(ClientRoutes::class.java)
    }

    fun getProvidersRoutes(): ProviderRoutes {
        return retrofit.getClient(API_URL).create(ProviderRoutes::class.java)
    }

    fun getProductsRoutes(): ProductRoutes {
        return retrofit.getClient(API_URL).create(ProductRoutes::class.java)
    }
}