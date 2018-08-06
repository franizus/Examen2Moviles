package com.example.frani.examen1moviles

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import kotlinx.android.synthetic.main.activity_create_book.*
import java.io.ByteArrayOutputStream

class CreateBookActivity : AppCompatActivity() {

    var idAutor: Int = 0
    var idLibro: Int = 0
    var libro: Libro? = null
    var tipo = false
    lateinit var imageBitmap: Bitmap
    lateinit var myBase64Image: String
    lateinit var myBitmapAgain: Bitmap

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

        btnPicture.setOnClickListener{
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
        var price = txtPriceLibro.text.toString().toInt()
        var imagen = myBase64Image
        var libro = Libro(this.idLibro, isbn, nombre, numeroPaginas, edicion, fechaP, nombreEd, price, imagen, idAutor, 0, 0)

        if (!tipo) {
            DataBaseLibro.insertarLibro(libro)
        } else {
            DataBaseLibro.updateLibro(libro)
        }

        finish()
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun tomarFoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap

            myBase64Image = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100)
            myBitmapAgain = decodeBase64(myBase64Image)

            imageViewPicture.setImageBitmap(myBitmapAgain)
        }

    }

    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat, quality: Int): String {
        val byteArrayOS = ByteArrayOutputStream()
        image.compress(compressFormat, quality, byteArrayOS)
        return android.util.Base64.encodeToString(byteArrayOS.toByteArray(), android.util.Base64.DEFAULT)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}
