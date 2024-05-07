package com.appsnica.consumosencillo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appsnica.consumosencillo.databinding.ActivityMainBinding
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.awaitResponse
import com.github.kittinunf.fuel.coroutines.awaitString
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(true)
        //Realizar la solicitud Get al API web con fuel
        loadUsers()

        /*Fuel.get("https://663917474253a866a2504bf9.mockapi.io/EjemploApi/Users")
            .response(){ request, response, result ->
                //Obtenemos la respuesta de la solicitud en formato json
                val jsonBody = response.body().asString("application/json")
                //declaramos un objeto Gson para poder realizar la deserealización
                // y guardamos el resultado en la lista de usurios
                val gson = Gson()
                listaUsuarios = gson.fromJson(jsonBody, Array<User>::class.java).toList()

                //Recuperamos el total de los valores de la lista
                binding.txttext1.setText("Total registro: ${listaUsuarios.size}")

                //Llenamos el RecyclerView con los elementos de la lista
                //mediante el adaptador creado
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = UserAdapter(listaUsuarios)
                showLoading(false)
            }*/
    }



    private fun loadUsers() {
        // Utiliza GlobalScope.launch para lanzar una nueva corrutina en el hilo de IO
        GlobalScope.launch(Dispatchers.IO) {
            try {
                // Realiza una solicitud HTTP GET a la API y espera la respuesta como una cadena
                val response = Fuel.get("https://663917474253a866a2504bf9.mockapi.io/EjemploApi/Users").awaitString()

                // Utiliza withContext para cambiar al hilo principal y actualizar la IU
                withContext(Dispatchers.Main) {
                    // Inicializa Gson para convertir la respuesta JSON en una lista de usuarios
                    val gson = Gson()
                    // Convierte la respuesta JSON en una lista de usuarios utilizando Gson
                    listaUsuarios = gson.fromJson(
                        response, Array<User>::class.java
                    ).toList()
                    // Actualiza un TextView para mostrar la cantidad de elementos en la lista
                    binding.txttext1.setText("Hay ${listaUsuarios.size} elementos")
                    // Configura el LinearLayoutManager para el RecyclerView
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    // Crea y establece un adaptador de usuarios para el RecyclerView
                    binding.recyclerView.adapter = UserAdapter(listaUsuarios)
                    // Oculta el indicador de carga después de cargar los usuarios
                    showLoading(false)
                }
            } catch (ex: Exception) {
                // Maneja cualquier excepción que pueda ocurrir durante la carga de usuarios
                withContext(Dispatchers.Main) {
                    // Muestra un mensaje de error en un Toast
                    Toast.makeText(
                        this@MainActivity,
                        "Error:  ${ex.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    // Actualiza un TextView para indicar que no hay conexión en este momento
                    binding.txttext1.setText("En este momento no dispone de conexión")
                    // Oculta el indicador de carga después de manejar el error
                    showLoading(false)
                }
            }
        }
    }


    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}