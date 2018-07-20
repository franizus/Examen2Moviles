package com.example.frani.examen1moviles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_book.*

class CreateBookActivity : AppCompatActivity() {

    var idAutor: Int = 0
    var idLibro: Int = 0
    var libro: Libro? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewLibro.text = getString(R.string.edit_book)
            libro = intent.getParcelableExtra("libro")
            idLibro = libro!!.id
            fillFields()
            tipo = true
        }

        idAutor = intent.getIntExtra("idAutor", 0)

        btnCrearLibro.setOnClickListener{
            v: View? ->  crearLibro()
        }
    }

    fun fillFields() {
        txtISBNLibro.setText(libro?.isbn)
        txtNombreLibro.setText(libro?.nombre)
        txtNPagLibro.setText(libro?.numeroPaginas.toString())
        txtEdicionLibro.setText(libro?.edicion.toString())
        txtFechaPLibro.setText(libro?.fechaPublicacion)
        txtEditorialLibro.setText(libro?.nombreEditorial)
    }

    fun crearLibro() {
        var isbn = txtISBNLibro.text.toString()
        var nombre = txtNombreLibro.text.toString()
        var numeroPaginas = txtNPagLibro.text.toString().toInt()
        var edicion = txtEdicionLibro.text.toString().toInt()
        var fechaP = txtFechaPLibro.text.toString()
        var nombreEd = txtEditorialLibro.text.toString()
        var latitud = txtLatLibro.text.toString().toDouble()
        var longitud = txtLongLibro.text.toString().toDouble()
        var libro = Libro(this.idLibro, isbn, nombre, numeroPaginas, edicion, fechaP, nombreEd, latitud, longitud, idAutor, 0, 0)

        if (!tipo) {
            DataBaseLibro.insertarLibro(libro)
        } else {
            DataBaseLibro.updateLibro(libro)
        }

        finish()
    }
}
