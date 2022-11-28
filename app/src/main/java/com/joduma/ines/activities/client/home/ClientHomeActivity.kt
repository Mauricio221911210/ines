package com.joduma.ines.activities.client.home


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.joduma.ines.MainActivity
import com.joduma.ines.R
import com.joduma.ines.activities.RegisterActivity
import com.joduma.ines.activities.RegisterClieActivity
import com.joduma.ines.activities.RegisterProActivity
import com.joduma.ines.activities.RegisterProvActivity
import com.joduma.ines.models.User
import com.joduma.ines.utils.SharedPref


class ClientHomeActivity : AppCompatActivity() {


    private val TAG = "ClientHomeActivity"
    var buttonLogout: Button? = null
    var sharedPref: SharedPref? = null
    var buttonClient: ImageView? = null
    var buttonUser: ImageView? = null
    var buttonProduct: ImageView? = null
    var buttonProvider: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)
        sharedPref = SharedPref(this)


        buttonClient = findViewById(R.id.user_cli)
        buttonClient?.setOnClickListener { client() }

        buttonUser = findViewById(R.id.user)
        buttonUser?.setOnClickListener { user() }

        buttonProduct = findViewById(R.id.product_user)
        buttonProduct?.setOnClickListener { product() }

        buttonProvider= findViewById(R.id.provider_user)
        buttonProvider?.setOnClickListener { provider() }

        buttonLogout = findViewById(R.id.btn_logout)
        buttonLogout?.setOnClickListener {  logout() }


        getUserFromSession()

    }

    private fun client(){
        val i = Intent(this, RegisterClieActivity::class.java)
        startActivity(i)
    }

    private fun user(){
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    private fun provider(){
        val i = Intent(this, RegisterProvActivity::class.java)
        startActivity(i)
    }

    private fun product(){
        val i = Intent(this, RegisterProActivity::class.java)
        startActivity(i)
    }

    private fun logout(){
        sharedPref?.remove("user")
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){


        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            // Si el usuario existe en sesion
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }

        }
    }