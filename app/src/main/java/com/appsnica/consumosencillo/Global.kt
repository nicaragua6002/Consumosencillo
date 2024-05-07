package com.appsnica.consumosencillo

import com.google.gson.annotations.SerializedName

//Definicion de las clases a deserializar con Gson
data class User(val idUser: String, val name: String, val address: String)
data class Login(val id: Int,val user: String,  @SerializedName("password") val pass: String )

//Listas con los valores a cargar desde el servicio web
lateinit var listaUsuarios: List<User>
lateinit var listaLogin: List<Login>