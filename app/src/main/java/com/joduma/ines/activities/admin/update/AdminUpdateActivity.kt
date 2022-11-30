package com.joduma.ines.activities.admin.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import com.joduma.ines.R
import com.joduma.ines.models.User
import com.joduma.ines.utils.SharedPref

class AdminUpdateActivity : AppCompatActivity() {

    var editTextName : EditText? = null
    var editTextLastname : EditText? = null
    var editTextPassword : EditText? = null
    var buttonUpdate : Button? = null
    var sharedPref: SharedPref? = null
    var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_update)
        getUserFromSession()

        editTextName = findViewById(R.id.edittext_name)
        editTextLastname = findViewById(R.id.edittext_lastname)
        editTextPassword = findViewById(R.id.edittext_password)
        buttonUpdate = findViewById(R.id.btn_save_changes)

        editTextName?.setText(user?.name)
        editTextLastname?.setText(user?.lastname)
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }
}