package com.joduma.ines.activities.admin.update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.joduma.ines.R
import com.joduma.ines.activities.admin.home.AdminHomeActivity
import com.joduma.ines.fragments.admin.ProfileFragment
import com.joduma.ines.models.ResponseHttp
import com.joduma.ines.models.User
import com.joduma.ines.providers.UsersProvider
import com.joduma.ines.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminUpdateActivity : AppCompatActivity() {

    var editTextName : EditText? = null
    var editTextLastname : EditText? = null
    var editTextPassword : EditText? = null
    var buttonUpdate : Button? = null
    var sharedPref: SharedPref? = null
    var user : User? = null
    var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_update)
        sharedPref = SharedPref(this)

        editTextName = findViewById(R.id.et_name)
        editTextLastname = findViewById(R.id.et_lastname)
        editTextPassword = findViewById(R.id.et_password)
        buttonUpdate = findViewById(R.id.btn_save_changes)

        getUserFromSession()

        editTextName?.setText(user?.name)
        editTextLastname?.setText(user?.lastname)
        buttonUpdate?.setOnClickListener { updateData() }
    }

    private fun updateData(){
        user?.name = editTextName?.text.toString();
        user?.lastname = editTextLastname?.text.toString()
        user?.password = editTextPassword?.text.toString()

        if (isValidForm(user?.name!!, user?.lastname!!)) {

            val body = User(
                id = user?.id!!,
                name = user?.name!!,
                lastname = user?.lastname!!,
                email =  user?.email!!,
                username = user?.username!!
            )

            if(!editTextPassword?.text.isNullOrBlank()) body.password = editTextPassword?.text.toString()

            Log.d("AdminUpdateActivity", "User : ${body}")
            usersProvider.update(body, user?.id!!)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(
                    call: Call<ResponseHttp>, response: Response<ResponseHttp>
                ) {
                    Toast.makeText(
                        this@AdminUpdateActivity,
                        "Datos Actualizados con Exito",
                        Toast.LENGTH_LONG
                    ).show()
                    saveUserInSession(response.body()?.user.toString())
                    gotToProfile()
                }

                override fun onFailure(p0: Call<ResponseHttp>, t: Throwable) {
                    Log.d("AdminUpdateActivity", "Hubo un error")
                    Toast.makeText(
                        this@AdminUpdateActivity,
                        "Los Datos no son Correctos",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
        } else {
            Toast.makeText(this, "Nombre y Apellido son Obligatorio", Toast.LENGTH_LONG).show()
        }
    }

    private fun isValidForm(name: String, lastname: String): Boolean {
        if (name.isBlank()) return false
        if (lastname.isBlank()) return false
        return true
    }

    private fun gotToProfile(){
        val i = Intent(this, AdminHomeActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }

    private fun saveUserInSession(data: String) {
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }
}