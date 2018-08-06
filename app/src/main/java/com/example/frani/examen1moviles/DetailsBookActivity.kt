package com.example.frani.examen1moviles

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_details_book.*

class DetailsBookActivity : AppCompatActivity() {

    var libro: Libro? = null
    lateinit var myBitmapAgain: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_book)

        libro = intent.getParcelableExtra("libro")

        txtShowIsbn.text = libro?.isbn
        txtShowNombreLibro.text = libro?.nombre
        txtShowNumPagLibro.text = libro?.numeroPaginas.toString()
        txtShowEdicLibro.text = libro?.edicion.toString()
        txtShowFechaPLibro.text = libro?.fechaPublicacion
        txtShowEditorialLibro.text = libro?.nombreEditorial
        txtShowPriceLibro.text = libro?.precio.toString()

        myBitmapAgain = decodeBase64(libro?.imagen!!)
        imageViewPicDet.setImageBitmap(myBitmapAgain)
    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}
