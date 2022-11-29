package com.joduma.ines.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


class ResponseHttp (
    @SerializedName("user") val user: JsonObject,
    @SerializedName("token") val token: String,
    @SerializedName("message") val message: String
) {
    override fun toString(): String {
        return "ResponseHttp(user=$user, token='$token')"
    }
}