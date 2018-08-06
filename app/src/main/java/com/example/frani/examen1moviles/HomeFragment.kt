package com.example.frani.examen1moviles

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var adaptador: LibroCostumerAdapter
    lateinit var libros: ArrayList<Libro>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(R.layout.fragment_home, container, false)

        libros = DataBaseLibro.getLibros()

        val layoutManager = LinearLayoutManager(activity)
        adaptador = LibroCostumerAdapter(libros)
        recyclerhome.layoutManager = layoutManager
        recyclerhome.itemAnimator = DefaultItemAnimator()
        recyclerhome.adapter = adaptador
        adaptador.notifyDataSetChanged()

        return rootView
    }

}