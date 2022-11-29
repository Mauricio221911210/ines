package com.joduma.ines.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.joduma.ines.R
import com.joduma.ines.activities.client.home.ClientHomeActivity
import com.joduma.ines.models.Product
import com.joduma.ines.models.ResponseHttp
import com.joduma.ines.providers.UsersProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class RegisterProActivity : AppCompatActivity() {

    val  TAG = "RegisterProActivity"

    var imageViewGoToMenu: ImageView? = null
    var imageViewGoToList: ImageView? = null
    var editTextCode: EditText? = null
    var editTextDescription: EditText? = null
    var editTextProviderid: EditText? = null
    var editTextProviderPrice: EditText? = null
    var editTextPrice: EditText? = null
    var editTextStock: EditText? = null
    var editTextUserID: EditText? = null
    var buttonRegisterPro: Button? = null

    var usersProvider = UsersProvider()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_pro)

        imageViewGoToMenu = findViewById(R.id.imageview_go_to_menu)
        editTextCode = findViewById(R.id.edittext_code)
        editTextDescription = findViewById(R.id.edittext_description)
        editTextProviderid = findViewById(R.id.edittext_provider)
        editTextProviderPrice = findViewById(R.id.edittext_precio_provider)
        editTextPrice = findViewById(R.id.edittext_price)
        editTextStock = findViewById(R.id.edittext_stock)
        editTextUserID = findViewById(R.id.edittext_user_id)
        buttonRegisterPro = findViewById(R.id.btn_registerpro)



        imageViewGoToMenu = findViewById(R.id.imageview_go_to_menu)
        imageViewGoToMenu?.setOnClickListener{ goToMenu() }
        buttonRegisterPro?.setOnClickListener { registerpro() }



    }

    private fun registerpro(){
        val code = editTextCode?.text.toString()
        val description = editTextDescription?.text.toString()
        val provider_id = editTextProviderid?.text.toString()
        val provider_price = editTextProviderPrice?.text.toString()
        val price = editTextPrice?.text.toString()
        val stock = editTextStock?.text.toString()
        val userid = editTextUserID?.text.toString()

        if (isValidForm(code = code, description = description,
                provider_id = provider_id  ,provider_price = provider_price, price = price, stock = stock, user_id = userid )) {

            val product = Product(
                code = code,
                description = description,
                provider_id = provider_id,
                provider_price = provider_price,
                price = price,
                stock = stock,
                user_id = userid
            )

            usersProvider.product(product)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(Call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Toast.makeText( this@RegisterProActivity, response.message(), Toast.LENGTH_LONG).show()


                    Log.d(TAG, "Response: exito " )
                    Log.d(TAG, "Body: exito " )
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Se produjo un error ")
                    Toast.makeText( this@RegisterProActivity,"Se produjo un error ", Toast.LENGTH_LONG).show()
                }

            })
        }


    }


   private fun isValidForm(
        code: String,
        description: String,
        provider_id: String,
        provider_price: String,
        price: String,
        stock: String,
        user_id: String,
    ): Boolean {

        if (code.isBlank()) {
            Toast.makeText(this, "Debes ingresar el nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (description.isBlank()) {
            Toast.makeText(this, "Debes ingresar el apellido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (provider_id.isBlank()) {
            Toast.makeText(this,"Debes ingresar el username", Toast.LENGTH_SHORT).show()
            return false
        }



        if (provider_price.isBlank()) {
            Toast.makeText(this, "Debes ingresar el email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (price.isBlank()) {
            Toast.makeText(this, "Debes ingresar el contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if (stock.isBlank()) {
            Toast.makeText(this, "Debes ingresar el contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if (user_id.isBlank()) {
            Toast.makeText(this, "Debes ingresar el contraseña", Toast.LENGTH_SHORT).show()
            return false
        }




        return true
    }

    private fun goToMenu (){
        val i = Intent( this, ClientHomeActivity::class.java )
        startActivity(i)
    }

    private fun goToList (){
        val i = Intent( this, ListProductActivity::class.java )
        startActivity(i)
    }


    }
