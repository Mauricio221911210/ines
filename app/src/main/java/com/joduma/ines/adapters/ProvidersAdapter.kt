package com.joduma.ines.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joduma.ines.R
import com.joduma.ines.activities.admin.home.AdminHomeActivity
import com.joduma.ines.models.Provider
import com.joduma.ines.utils.SharedPref

class ProvidersAdapter(val context: Activity, val providers: ArrayList<Provider>): RecyclerView.Adapter<ProvidersAdapter.ProvidersViewHolder>() {

    val sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvidersViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_client, parent, false)
        return ProvidersViewHolder(view)
    }

    override fun getItemCount(): Int{
        return providers.size
    }

    override fun onBindViewHolder(holder: ProvidersViewHolder, position: Int){
        val provider = providers[position]

        holder.textViewClientName.text = "${provider.name}"
        holder.textViewClientPhone.text = provider.phone
        holder.textViewClientAddress.text = provider.address

//        holder.itemView.setOnClickListener { goToClient(provider) }
    }

    fun goToClient(provider: Provider){
        val i = Intent(context, AdminHomeActivity::class.java)
        context.startActivity(i)
    }

    class ProvidersViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewClientName: TextView
        val textViewClientPhone: TextView
        val textViewClientAddress: TextView

        init {
            textViewClientName = view.findViewById(R.id.tv_client_name)
            textViewClientPhone = view.findViewById(R.id.tv_client_phone)
            textViewClientAddress = view.findViewById(R.id.tv_client_address)
        }
    }

}