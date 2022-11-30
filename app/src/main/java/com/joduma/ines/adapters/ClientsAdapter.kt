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
import com.joduma.ines.models.Client
import com.joduma.ines.utils.SharedPref

class ClientsAdapter(val context: Activity, val clients: ArrayList<Client>): RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder>() {

    val sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_client, parent, false)
        return ClientsViewHolder(view)
    }

    override fun getItemCount(): Int{
        return clients.size
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int){
        val client = clients[position]

        holder.textViewClientName.text = "${client.name} ${client.lastname}"
        holder.textViewClientPhone.text = client.phone
        holder.textViewClientAddress.text = client.address

        holder.itemView.setOnClickListener { goToClient(client) }
    }

    fun goToClient(client: Client){
        val i = Intent(context, AdminHomeActivity::class.java)
        context.startActivity(i)
    }

    class ClientsViewHolder(view: View): RecyclerView.ViewHolder(view){
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