package com.example.frani.examen1moviles

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class LibroCostumerAdapter(private val librosList: List<Libro>) : RecyclerView.Adapter<LibroCostumerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombre: TextView
        var fechaPublicacion: TextView
        var editorial: TextView
        var detalles: Button
        var agregar: Button
        lateinit var libro: Libro

        init {
            nombre = view.findViewById(R.id.txt_11) as TextView
            fechaPublicacion = view.findViewById(R.id.txt_21) as TextView
            editorial = view.findViewById(R.id.txt_31) as TextView
            detalles = view.findViewById(R.id.btn_11) as Button
            agregar = view.findViewById(R.id.btn_21) as Button
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclercustomer, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val libro = librosList[position]
        holder.nombre.text = libro.nombre
        holder.fechaPublicacion.text = libro.fechaPublicacion
        holder.editorial.text = libro.nombreEditorial
        holder.libro = libro
        holder.agregar.setOnClickListener{
            v: View ->
            val detalleOrden = DetalleOrden(0,0,libro.id,libro.precio,0,0)
            Factory.orden.add(detalleOrden)
        }
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetailsBookActivity::class.java)
            intent.putExtra("libro", libro)
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return librosList.size
    }

}