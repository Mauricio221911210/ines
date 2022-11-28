package com.joduma.ines.providers

import com.joduma.ines.api.ApiRoutes
import com.joduma.ines.models.Client
import com.joduma.ines.models.ResponseHttp
import com.joduma.ines.routes.ClientRoutes
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call

class ClientsProvider {

    private var clientRoutes: ClientRoutes? = null

    init {
        val api = ApiRoutes()
        clientRoutes = api.getClientsRoutes();
    }

    fun create(client: Client): Call<ResponseHttp>? {
        val requestBody = RequestBody.create(MediaType.parse("text/plain"), client.toJson())
        return clientRoutes?.create(requestBody)
    }
}