package com.joduma.ines.models

import android.location.Address
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Client(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String? = null,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: String,
    @SerializedName("user_id") val user_id: String? = null,
) {

    override fun toString(): String {
        return "Client(id=$id, name='$name', lastname='$lastname', phone='$phone', address=$address, user_id='$user_id')"
    }

    fun toJson(): String{
        return Gson().toJson(this)
    }
}