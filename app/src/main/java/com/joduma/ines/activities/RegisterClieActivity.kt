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
import com.joduma.ines.models.Client
import com.joduma.ines.models.ResponseHttp
import com.joduma.ines.providers.UsersProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterClieActivity : AppCompatActivity() {

    val  TAG = "RegisterClieActivity"

    var imageViewGoToMenu: ImageView? = null
    var editTextname: EditText? = null
    var editTextlastanme: EditText? = null
    var editTextPhone: EditText? = null
    var editTextaddress: EditText? = null
    var editTextUserID: EditText? = null
    var buttonRegisterCli: Button? = null

    var usersProvider = UsersProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_clie)


        imageViewGoToMenu = findViewById(R.id.imageview_go_to_menu)
        editTextname = findViewById(R.id.edittext_namecli)
        editTextlastanme = findViewById(R.id.edittext_lastnamecli)
        editTextPhone = findViewById(R.id.edittext_phone)
        editTextaddress = findViewById(R.id.edittext_address)
        editTextUserID = findViewById(R.id.edittext_user_id_cli)
        buttonRegisterCli = findViewById(R.id.btn_registercli)



        imageViewGoToMenu = findViewById(R.id.imageview_go_to_menu)
        imageViewGoToMenu?.setOnClickListener{ goToMenu() }
        buttonRegisterCli?.setOnClickListener { registercli() }



    }

    private fun registercli(){
        val name = editTextname?.text.toString()
        val lastname = editTextlastanme?.text.toString()
        val phone = editTextPhone?.text.toString()
        val address = editTextaddress?.text.toString()
        val user_id = editTextUserID?.text.toString()






        if (isValidForm(name = name, lastname = lastname, phone = phone, address = address, user_id = user_id )) {

            val client = Client(
               name = name,
                lastname = lastname,
                phone = phone,
               address = address,
                user_id = user_id,
            )

            usersProvider.client(client)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(Call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Toast.makeText( this@RegisterClieActivity, response.message(), Toast.LENGTH_LONG).show()


                    Log.d(TAG, "Response: exito " )
                    Log.d(TAG, "Body: exito " )
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG, "Se produjo un error ")
                    Toast.makeText( this@RegisterClieActivity,"Se produjo un error ", Toast.LENGTH_LONG).show()
                }

            })
        }


    }


    private fun isValidForm(
        name: String,
        lastname: String,
        phone: String,
        address: String,
        user_id: String,


    ): Boolean {

        if (name.isBlank()) {
            Toast.makeText(this, "Debes ingresar el nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (lastname.isBlank()) {
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


}
