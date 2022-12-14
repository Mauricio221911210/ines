package com.joduma.ines.models

import com.google.gson.annotations.SerializedName

class Product(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("code") val code: String,
    @SerializedName("description") val description: String,
    @SerializedName("provider_id") val provider_id: String,
    @SerializedName("price") val price: String,
    @SerializedName("provider_price") val provider_price: String,
    @SerializedName("stock") val stock: String,
    @SerializedName("wholesale_price") val wholesale_price: String? = null,
    @SerializedName("wholesale_quantity") val wholesale_quantity: String? = null,
    @SerializedName("photo") val photo: String? = null,
    @SerializedName("status") val status: String? = "1",
    @SerializedName("user_id") val user_id: String,
) {
    override fun toString(): String {
        return "Product(id=$id, code='$code', description='$description', provider_id='$provider_id', price='$price', provider_price='$provider_price', stock='$stock', wholesale_price=$wholesale_price, wholesale_quantity=$wholesale_quantity, photo=$photo, status=$status, user_id='$user_id')"
    }
}































































