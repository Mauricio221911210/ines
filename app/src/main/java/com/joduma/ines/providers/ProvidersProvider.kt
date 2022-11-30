package com.joduma.ines.providers

import com.joduma.ines.api.ApiRoutes
import com.joduma.ines.models.Provider
import com.joduma.ines.routes.ProviderRoutes
import retrofit2.Call

class ProvidersProvider {

    private var providerRoutes: ProviderRoutes? = null

    init {
        val api = ApiRoutes()
        providerRoutes = api.getProvidersRoutes();
    }

    fun index(): Call<ArrayList<Provider>>? {
        return providerRoutes?.index()
    }
}