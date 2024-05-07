package com.appsnica.consumosencillo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LoginAdapter(val listaLogin: List<Login>):
    RecyclerView.Adapter<LoginAdapter.LoginViewHolder>() {

    // Clase interna que representa cada elemento de la lista en la vista
    class LoginViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //Obtenemos los componentes de la vista
        private val txtnombre= itemView.findViewById<TextView>(R.id.txtUsuario)
        private val txtpass= itemView.findViewById<TextView>(R.id.txtdireccion)

        fun bind(login: Login){
            txtnombre.text= login.user
            txtpass.text = login.pass
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return LoginViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaLogin.size
    }

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) {
        val login = listaLogin[position]
        holder.bind(login)
    }
}