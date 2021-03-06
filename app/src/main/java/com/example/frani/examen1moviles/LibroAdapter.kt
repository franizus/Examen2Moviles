package com.example.frani.examen1moviles

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class LibroAdapter(private val librosList: List<Libro>) : RecyclerView.Adapter<LibroAdapter.MyViewHolder>() {

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        var nombre: TextView
        var fechaPublicacion: TextView
        var editorial: TextView
        var detalles: Button
        lateinit var libro: Libro

        init {
            nombre = view.findViewById(R.id.txt_1) as TextView
            fechaPublicacion = view.findViewById(R.id.txt_2) as TextView
            editorial = view.findViewById(R.id.txt_3) as TextView
            detalles = view.findViewById(R.id.btn_1)as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, R.string.menu_edit)
            menu?.add(Menu.NONE, R.id.item_menu_borrar, Menu.NONE, R.string.menu_delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val libro = librosList[position]
        holder.nombre.text = libro.nombre
        holder.fechaPublicacion.text = libro.fechaPublicacion
        holder.editorial.text = libro.nombreEditorial
        holder.libro = libro
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetailsBookActivity::class.java)
            intent.putExtra("libro", libro)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return librosList.size
    }

}