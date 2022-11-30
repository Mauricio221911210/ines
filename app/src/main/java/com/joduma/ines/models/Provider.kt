package com.joduma.ines.models

import com.google.gson.annotations.SerializedName

class Provider (
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: String,
    @SerializedName("status") val status: String? = "1"
){

    override fun toString(): String {
        return "Provider(id=$id, name='$name', contact='$contact', phone='$phone', address='$address', status=$status)"
    }
}