package com.example.frani.examen1moviles

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBUser {
    companion object {
        val DB_NAME = "USER"
        val TABLE_NAME = "users"
        val CAMPO_ID = "id"
        val ID = "idAPI"
        val ROL = "rol"
        val CAMPO_USUARIO = "nombre"
    }
}

class DBUserHandlerAplicacion(context: Context) : SQLiteOpenHelper(context, DBUser.DB_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL = "CREATE TABLE ${DBUser.TABLE_NAME} (${DBUser.CAMPO_ID} INTEGER PRIMARY KEY, ${DBUser.ID} INTEGER, ${DBUser.ROL} INTEGER, ${DBUser.CAMPO_USUARIO} VARCHAR(50))"
        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertarUser(user: User) {
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        cv.put(DBUser.CAMPO_ID, 1)
        cv.put(DBUser.ID, user.id)
        cv.put(DBUser.ROL, user.rol)
        cv.put(DBUser.CAMPO_USUARIO, user.username)

        val resultado = dbWriteable.insert(DBUser.TABLE_NAME, null, cv)

        dbWriteable.close()
    }

    fun deleteUser(id: Int): Boolean {
        val dbWriteable = writableDatabase
        val whereClause = "${DBUser.CAMPO_ID} = $id"
        return dbWriteable.delete(DBUser.TABLE_NAME, whereClause, null) > 0
    }

    fun getUser(): User? {
        val dbReadable = readableDatabase
        val query = "SELECT * FROM ${DBUser.TABLE_NAME}"
        val resultado = dbReadable.rawQuery(query, null)
        var user: User? = null

        if (resultado.moveToFirst()) {
            do {
                val id = resultado.getString(1).toInt()
                val rol = resultado.getString(2).toInt()
                val username = resultado.getString(3)

                user = User(id, rol, username, "")
            } while (resultado.moveToNext())
        }

        resultado.close()
        dbReadable.close()

        return user
    }

}