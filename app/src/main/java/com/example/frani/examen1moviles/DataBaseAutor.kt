package com.example.frani.examen1moviles

import android.util.Log
import java.io.StringReader
import android.os.StrictMode
import com.beust.klaxon.*
import com.github.kittinunf.fuel.*


class DataBaseAutor {

    companion object {

        fun insertarAutor(autor: Autor) {
            "http://192.168.100.159:1337/Autor".httpPost(listOf("nombre" to autor.nombre, "apellido" to autor.apellido, "fechaNacimiento" to autor.fechaNacimiento, "numeroLibros" to autor.numeroLibros, "ecuatoriano" to autor.ecuatoriano))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updateAutor(autor: Autor) {
            "http://192.168.100.159:1337/Autor/${autor.id}".httpPatch(listOf("nombre" to autor.nombre, "apellido" to autor.apellido, "fechaNacimiento" to autor.fechaNacimiento, "numeroLibros" to autor.numeroLibros, "ecuatoriano" to autor.ecuatoriano))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun deleteAutor(id: Int) {
            "http://192.168.100.159:1337/Autor/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Autor> {
            val autores: ArrayList<Autor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.100.159:1337/Autor".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellido = it["apellido"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numeroLibros = it["numeroLibros"] as Int
                val ecuatoriano = it["ecuatoriano"] as Int
                val autor = Autor(id, nombre, apellido, fechaNacimiento, numeroLibros, ecuatoriano, 0, 0)
                autores.add(autor)
            }
            return autores
        }

    }

}
