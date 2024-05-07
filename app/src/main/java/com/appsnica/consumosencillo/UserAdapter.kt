package com.appsnica.consumosencillo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val users: List<User>): // Define la clase del adaptador y recibe una lista de usuarios como parámetro
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() { // Extiende RecyclerView.Adapter y especifica el tipo de ViewHolder

    // Clase interna que representa cada elemento de la lista en la vista
    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // Define los campos del itemView
        private val txtNombre = itemView.findViewById<TextView>(R.id.txtUsuario)
        private val txtDireccion = itemView.findViewById<TextView>(R.id.txtdireccion)

        // Función para vincular el valor con el campo del itemView
        fun bind(user: User){
            txtNombre.text = user.idUser + " - " + user.name // Asigna el nombre del usuario al campo de nombre
            txtDireccion.text = user.address // Asigna la dirección del usuario al campo de dirección
        }
    }

    // Método llamado cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Define el diseño de los elementos de la vista
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user,parent,false)
        // Crea un nuevo objeto UserViewHolder con la vista inflada
        return UserViewHolder(view)
    }

    // Método que devuelve el número total de elementos en la lista
    override fun getItemCount(): Int {
        return users.size
    }

    // Método llamado cuando RecyclerView necesita mostrar un elemento en una posición específica
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position] // Obtiene el usuario en la posición dada
        holder.bind(user) // Llama a la función bind del ViewHolder para actualizar los campos de la vista con los datos del usuario
    }
}
