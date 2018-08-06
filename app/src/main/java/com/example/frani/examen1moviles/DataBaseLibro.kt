package com.example.frani.examen1moviles

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.*
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPatch
import com.github.kittinunf.fuel.httpPost
import java.io.StringReader

class DataBaseLibro {

    companion object {
        fun insertarLibro(libro: Libro) {
            "http://${Factory.ip}:1337/Libro".httpPost(listOf("isbn" to libro.isbn, "nombre" to libro.nombre, "numeroPaginas" to libro.numeroPaginas, "edicion" to libro.edicion, "fechaPublicacion" to libro.fechaPublicacion, "nombreEditorial" to libro.nombreEditorial, "precio" to libro.precio, "imagen" to libro.imagen, "autorId" to libro.autorID))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun updateLibro(libro: Libro) {
            "http://${Factory.ip}:1337/Libro/${libro.id}".httpPatch(listOf("isbn" to libro.isbn, "nombre" to libro.nombre, "numeroPaginas" to libro.numeroPaginas, "edicion" to libro.edicion, "fechaPublicacion" to libro.fechaPublicacion, "nombreEditorial" to libro.nombreEditorial, "precio" to libro.precio, "imagen" to libro.imagen, "autorId" to libro.autorID))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun deleteLibro(id: Int) {
            "http://${Factory.ip}:1337/Libro/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getLibrosList(idAutor: Int): ArrayList<Libro> {
            val libros: ArrayList<Libro> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://${Factory.ip}:1337/Libro?autorId=$idAutor".httpGet().responseString()
            val jsonStringLibro = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringLibro)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val isbn = it["isbn"] as String
                val nombre = it["nombre"] as String
                val numeroPaginas = it["numeroPaginas"] as Int
                val edicion = it["edicion"] as Int
                val fechaPublicacion = it["fechaPublicacion"] as String
                val nombreEditorial = it["nombreEditorial"] as String
                val precio = it["precio"] as Int
                val imagen = it["imagen"] as String
                val libro = Libro(id, isbn, nombre, numeroPaginas, edicion, fechaPublicacion, nombreEditorial, precio, imagen, idAutor, 0, 0)
                libros.add(libro)
            }
            return libros
        }

        fun getLibros(): ArrayList<Libro> {
            val libros: ArrayList<Libro> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://${Factory.ip}:1337/Libro".httpGet().responseString()
            val jsonStringLibro = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringLibro)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val isbn = it["isbn"] as String
                val nombre = it["nombre"] as String
                val numeroPaginas = it["numeroPaginas"] as Int
                val edicion = it["edicion"] as Int
                val fechaPublicacion = it["fechaPublicacion"] as String
                val nombreEditorial = it["nombreEditorial"] as String
                val precio = it["precio"] as Int
                val imagen = it["imagen"] as String
                val idAutor = it["idAutor"] as Int
                val libro = Libro(id, isbn, nombre, numeroPaginas, edicion, fechaPublicacion, nombreEditorial, precio, imagen, idAutor, 0, 0)
                libros.add(libro)
            }
            return libros
        }
    }

}
