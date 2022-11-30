package com.joduma.ines.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") var name: String,
    @SerializedName("lastname") var lastname: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("role_id") val role_id: Int?= 1,
    @SerializedName("password") var password: String? = "",
    ) {

    fun toJson(): String {
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', lastname='$lastname', username='$username', email='$email', role_id=$role_id, password='$password')"
    }


}