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
import com.joduma.ines.adapters.ProvidersAdapter
import com.joduma.ines.models.Provider
import com.joduma.ines.models.User
import com.joduma.ines.providers.ClientsProvider
import com.joduma.ines.providers.ProvidersProvider
import com.joduma.ines.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvidersFragment : Fragment() {

    val TAG = "ProvidersFragment"
    var myView: View? = null
    var recyclerViewProviders: RecyclerView? = null
    var providersProvider: ProvidersProvider? = null
    var adapter: ProvidersAdapter? = null
    var user: User? = null
    var sharedPref: SharedPref? = null
    var providers = ArrayList<Provider>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_clients, container, false)

        recyclerViewProviders = myView?.findViewById(R.id.recyclerview_clients)
        recyclerViewProviders?.layoutManager = LinearLayoutManager(requireContext())
        sharedPref = SharedPref(requireActivity())

        getUserFromSession()

        providersProvider = ProvidersProvider()

        getClients()
        return myView
    }

    private fun getClients(){
        providersProvider?.index()?.enqueue(object: Callback<ArrayList<Provider>>{
            override fun onResponse(
                call: Call<ArrayList<Provider>>,
                response: Response<ArrayList<Provider>>
            ) {
                if(response.body() != null){
                    providers = response.body()!!
                    adapter = ProvidersAdapter(requireActivity(), providers)
                    recyclerViewProviders?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Provider>>, t: Throwable) {
                Log.d(TAG, "Error: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getUserFromSession(){
        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }
}