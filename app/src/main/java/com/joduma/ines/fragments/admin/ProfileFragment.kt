package com.joduma.ines.fragments.admin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.joduma.ines.R
import com.joduma.ines.activities.admin.update.AdminUpdateActivity
import com.joduma.ines.models.User
import com.joduma.ines.utils.SharedPref

class ProfileFragment : Fragment() {

    var myView: View? = null
    var buttonUpdateProfile: Button? = null
    var textViewName: TextView? = null
    var textViewUsername: TextView? = null
    var textViewEmail: TextView? = null
    var textViewRole: TextView? = null

    var sharedPref: SharedPref? = null
    var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_profile, container, false)

        sharedPref = SharedPref(requireActivity())

        buttonUpdateProfile = myView?.findViewById(R.id.btn_update_profile)
        textViewName = myView?.findViewById(R.id.tv_name)
        textViewUsername = myView?.findViewById(R.id.tv_username)
        textViewEmail = myView?.findViewById(R.id.tv_email)
        textViewRole = myView?.findViewById(R.id.tv_role)

        getUserFromSession()

        val rol: String = when (user?.role_id){
            1 -> "Administrador"
            2 -> "Supervisor"
            3 -> "Vendedor"
            else -> "Desconocido"
        }

        textViewName?.setText("${user?.name} ${user?.lastname}")
        textViewUsername?.setText(user?.username)
        textViewRole?.setText(rol)
        textViewEmail?.setText(user?.email)

        buttonUpdateProfile?.setOnClickListener {
            goToUpdateProfile()
        }

        return myView
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }

    private fun goToUpdateProfile (){
        val i = Intent( requireContext(), AdminUpdateActivity::class.java )
        startActivity(i)
    }
}