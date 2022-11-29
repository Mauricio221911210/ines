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
import com.joduma.ines.models.Provider
import com.joduma.ines.models.ResponseHttp
import com.joduma.ines.providers.UsersProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterProvActivity : AppCompatActivity() {

    val  TAG = "RegisterClieActivity"

    var imageViewGoToMenu: ImageView? = null
    var editTextname: EditText? = null
    var editTextContact: EditText? = null
    var editTextPhone: EditText? = null
    var editTextaddress: EditText? = null
    var buttonRegisterProv: Button? = null


    var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_prov)

        imageViewGoToMenu = findViewById(R.id.imageview_go_to_menu)
        editTextname = findViewById(R.id.edittext_namecli)
        editTextContact = findViewById(R.id.edittext_contact)
        editTextPhone = findViewById(R.id.edittext_phone_prov)
        editTextaddress = findViewById(R.id.edittext_address_prov)



        imageViewGoToMenu = findViewById(R.id.imageview_go_to_menu)
        imageViewGoToMenu?.setOnClickListener{ goToMenu() }
        buttonRegisterProv?.setOnClickListener { registerprov() }



    }

    private fun registerprov(){
        val name = editTextname?.text.toString()
        val contact = editTextContact?.text.toString()
        val phone = editTextPhone?.text.toString()
        val address = editTextaddress?.text.toString()


        if (isValidForm(name = name, contact = contact, phone = phone, address = address,)) {

            val provider = Provider(
                name = name,
                contact = contact,
                phone = phone,
                address = address,

            )

            usersProvider.provider(provider)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(Call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Toast.makeText( this@RegisterProvActivity, response.message(), Toast.LENGTH_LONG).show()


                    Log.d(TAG, "Response: exito " )
                    Log.d(TAG, "Body: exito " )
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Se produjo un error ")
                    Toast.makeText( this@RegisterProvActivity,"Se produjo un error ", Toast.LENGTH_LONG).show()
                }

            })
        }


    }


    private fun isValidForm(
        name: String,
        contact: String,
        phone: String,
        address: String,


        ): Boolean {

        if (name.isBlank()) {
            Toast.makeText(this, "Debes ingresar el nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (contact.isBlank()) {
            Toast.makeText(this, "Debes ingresar el apellido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phone.isBlank()) {
            Toast.makeText(this,"Debes ingresar el username", Toast.LENGTH_SHORT).show()
            return false
        }



        if (address.isBlank()) {
            Toast.makeText(this, "Debes ingresar el email", Toast.LENGTH_SHORT).show()
            return false
        }




        return true
    }

    private fun goToMenu (){
        val i = Intent( this, ClientHomeActivity::class.java )
        startActivity(i)
    }


}

