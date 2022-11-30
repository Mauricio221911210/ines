package com.joduma.ines.activities.admin.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.joduma.ines.MainActivity
import com.joduma.ines.R
import com.joduma.ines.fragments.admin.ClientsFragment
import com.joduma.ines.fragments.admin.ProductsFragment
import com.joduma.ines.fragments.admin.ProfileFragment
import com.joduma.ines.fragments.admin.ProvidersFragment
import com.joduma.ines.models.User
import com.joduma.ines.utils.SharedPref
import kotlin.math.log

class AdminHomeActivity : AppCompatActivity() {

    private val TAG = "AdminHomeActivity"
    var buttonLogout: Button? = null
    var sharedPref: SharedPref? = null

    var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
        sharedPref = SharedPref(this)
        /*buttonLogout = findViewById(R.id.btn_logout)
        buttonLogout?.setOnClickListener{
            logout()
        }*/
        openFragment(ProfileFragment())
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId){
                R.id.item_profile -> {
                    openFragment(ProfileFragment())
                    true
                }
                R.id.item_products -> {
                    openFragment(ProductsFragment())
                    true
                }
                R.id.item_clients -> {
                    openFragment(ClientsFragment())
                    true
                }
                R.id.item_providers -> {
                    openFragment(ProvidersFragment())
                    true
                }
                R.id.item_logout -> {
                    logout()
                    true
                }

                else -> false
            }
        }

        getUserFromSession()
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun logout(){
        sharedPref?.remove("user")
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }
    }
}