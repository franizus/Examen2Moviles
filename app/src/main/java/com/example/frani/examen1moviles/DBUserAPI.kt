package com.example.frani.examen1moviles

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut

class DBUserAPI {

    companion object {

        fun insertarUser(user: User) {
            "http://40.117.248.211/Usuario".httpPost(listOf("rol" to user.rol, "username" to user.username, "password" to user.password))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updateUser(user: User) {
            "http://40.117.248.211/Usuario/${user.id}".httpPut(listOf("rol" to user.rol, "username" to user.username, "password" to user.password))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun deleteUser(id: Int) {
            "http://40.117.248.211/Usuario/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getUser(username: String): User? {
            var user: User? = null
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://40.117.248.211/Usuario?username=$username".httpGet().responseString()
            val jsonStringLibro = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringLibro)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val rol = it["rol"] as Int
                val username = it["username"] as String
                val password = it["password"] as String
                user = User(id, rol, username, password)
            }

            return user
        }

    }

}