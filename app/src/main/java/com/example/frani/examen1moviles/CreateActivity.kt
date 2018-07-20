package com.example.frani.examen1moviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    var autor: Autor? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewAutor.text = getString(R.string.edit_autor)
            autor = intent.getParcelableExtra("autor")
            fillFields()
            tipo = true
        }

        btnCrearAutor.setOnClickListener{
            v: View? -> crearAutor()
        }
    }

    fun fillFields() {
        txtNombreAutor.setText(autor?.nombre)
        txtApellidoAutor.setText(autor?.apellido)
        txtFechaAutor.setText(autor?.fechaNacimiento)
        txtNumeroLibrosAutor.setText(autor?.numeroLibros.toString())
        if (autor?.ecuatoriano == 1) {
            switchEcAutor.toggle()
        }
    }

    fun crearAutor() {
        var nombre = txtNombreAutor.text.toString()
        var apellido = txtApellidoAutor.text.toString()
        var fecha = txtFechaAutor.text.toString()
        var numeroLibros = txtNumeroLibrosAutor.text.toString().toInt()
        var ecutoriano = if (switchEcAutor.isChecked) 1 else 0

        if (!tipo) {
            var autor = Autor(0, nombre, apellido, fecha, numeroLibros, ecutoriano, 0, 0)
            DataBaseAutor.insertarAutor(autor)
        } else {
            var autor = Autor(autor?.id!!, nombre, apellido, fecha, numeroLibros, ecutoriano, 0, 0)
            DataBaseAutor.updateAutor(autor)
        }

        irAListView()
    }

    fun irAListView() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
}
