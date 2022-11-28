package com.joduma.ines

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.Gson
import com.joduma.ines.activities.RegisterActivity
import com.joduma.ines.activities.RegisterClieActivity
import com.joduma.ines.activities.RegisterProActivity
import com.joduma.ines.activities.RegisterProvActivity
import com.joduma.ines.models.ResponseHttp
import com.joduma.ines.providers.UsersProvider

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var imageViewGoToRegister: ImageView? = null
    var editTextEmail: EditText? = null
    var editTextUsername: EditText? = null
    var editTextPassword: EditText? = null
    var buttonLogin: Button? = null
    var usersProvider = UsersProvider()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewGoToRegister = findViewById(R.id.imageview_got_to_register)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextUsername = findViewById(R.id.edittext_username)
        editTextPassword = findViewById(R.id.edittext_password)
        buttonLogin = findViewById(R.id.btn_login)



        imageViewGoToRegister = findViewById(R.id.imageview_got_to_register)
        imageViewGoToRegister?.setOnClickListener { goToRegister() }
        buttonLogin?.setOnClickListener { login() }




    }



    private fun login() {
        val username = editTextUsername?.text.toString() // NULL POINTER EXCEPTION
        val password = editTextPassword?.text.toString()


        if (isValidForm(username, password)) {

            usersProvider.login(username, password)?.enqueue(object: Callback<ResponseHttp>{
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>
                ) {
                    Log.d("MainActivity", "Response : ${response.body()}")



                }

                override fun onFailure(p0: Call<ResponseHttp>, t: Throwable) {
                    Log.d("MainActivity", "Hubo un error")
                    Toast.makeText( this@MainActivity, "Hubo un error ", Toast.LENGTH_LONG).show()
                }


            })

            /*Toast.makeText(this, "El formulario es valido", Toast.LENGTH_LONG).show()*/
        }
        else {
            Toast.makeText(this, "No es valido", Toast.LENGTH_LONG).show()
        }

        /* Log.d("MainActivity", "El username es: $username")
         Log.d("MainActivity", "El password es: $password")*/
    }


    // fun String.isUsernameValid(): Boolean {
    //   return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    //}

    private fun isValidForm(username: String, password: String): Boolean {

        if (username.isBlank()) {
            return false
        }

        if (password.isBlank()) {
            return false
        }

        /* if (!username.isUsernameValid()) {
             return false
         }*/

        return true
    }

    private fun goToRegister (){
        val l = Intent(this, RegisterActivity::class.java)
        startActivity(l)
    }


}