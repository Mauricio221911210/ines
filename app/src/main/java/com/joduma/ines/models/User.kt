package com.joduma.ines.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("email_verified") val email_verified_at: String? = null,
    @SerializedName("role_id") val role_id: Int?= null,
    @SerializedName("password") val password: String,
    @SerializedName("remember_token") val remember_token: String? = null,

    ) {

    fun toJson(): String {
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', lastname='$lastname', username='$username', email='$email', image=$image, email_verified_at=$email_verified_at, role_id=$role_id, password='$password', remember_token=$remember_token)"
    }

}