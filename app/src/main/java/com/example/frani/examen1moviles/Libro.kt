package com.example.frani.examen1moviles

import android.os.Parcel
import android.os.Parcelable

class Libro(var id: Int,
            var isbn: String,
            var nombre: String,
            var numeroPaginas: Int,
            var edicion: Int,
            var fechaPublicacion: String,
            var nombreEditorial: String,
            var precio: Int,
            var imagen: String,
            var autorID: Int,
            var createdAt: Long,
            var updatedAt: Long) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(destino: Parcel?, p1: Int) {
        destino?.writeInt(id)
        destino?.writeString(isbn)
        destino?.writeString(nombre)
        destino?.writeInt(numeroPaginas)
        destino?.writeInt(edicion)
        destino?.writeString(fechaPublicacion)
        destino?.writeString(nombreEditorial)
        destino?.writeInt(precio)
        destino?.writeString(imagen)
        destino?.writeInt(autorID)
        destino?.writeLong(createdAt)
        destino?.writeLong(updatedAt)
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }

}