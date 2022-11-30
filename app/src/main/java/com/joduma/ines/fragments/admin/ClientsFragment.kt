package com.joduma.ines.fragments.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.joduma.ines.R
import com.joduma.ines.adapters.ClientsAdapter
import com.joduma.ines.models.Client
import com.joduma.ines.models.User
import com.joduma.ines.providers.ClientsProvider
import com.joduma.ines.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientsFragment : Fragment() {

    val TAG = "CategoriesFragment"
    var myView: View? = null
    var recyclerViewClients: RecyclerView? = null
    var clientsProvider: ClientsProvider? = null
    var adapter: ClientsAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var clients = ArrayList<Client>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_clients, container, false)

        recyclerViewClients = myView?.findViewById(R.id.recyclerview_clients)
        recyclerViewClients?.layoutManager = LinearLayoutManager(requireContext())
        sharedPref = SharedPref(requireActivity())

        getUserFromSession()

        clientsProvider = ClientsProvider()

        getClients()
        return myView
    }

    private fun getClients(){
        clientsProvider?.index()?.enqueue(object: Callback<ArrayList<Client>>{
            override fun onResponse(
                call: Call<ArrayList<Client>>,
                response: Response<ArrayList<Client>>
            ) {
                if(response.body() != null){
                    clients = response.body()!!
                    adapter = ClientsAdapter(requireActivity(), clients)
                    recyclerViewClients?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Client>>, t: Throwable) {
                Log.d(TAG, "Error: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }
    }
}