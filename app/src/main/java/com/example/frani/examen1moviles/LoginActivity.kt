package com.example.frani.examen1moviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var dbHandler: DBUserHandlerAplicacion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHandler = DBUserHandlerAplicacion(this)

        val user = dbHandler.getUser()

        if (user != null) {
            irActivity(user.rol)
        }

        btn_login.setOnClickListener{
            login()
        }
    }

    fun login() {
        if (!validate()) {
            onLoginFailed()
            return
        }

        btn_login.setEnabled(false)

        val email = input_email.text.toString()
        val password = input_password.text.toString()

        val user = DBUserAPI.getUser(email)
        if (user != null && user.password == password) {
            dbHandler.insertarUser(user)
            irActivity(user.rol)
            finish()
        } else {
            onLoginFailed()
            return
        }
    }

    fun irActivity(rol: Int) {
        when (rol) {
            1 -> {
                irACustomerActivity()
            }
            2 -> {
                irAListView()
            }
            3 -> {

            }
        }
    }

    fun irACustomerActivity() {
        val intent = Intent(this, CustomerActivity::class.java)
        startActivity(intent)
    }

    fun irAListView() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    fun onLoginFailed() {
        Toast.makeText(baseContext, getString(R.string.login_error), Toast.LENGTH_LONG).show()

        btn_login.setEnabled(true)
    }

    fun validate(): Boolean {
        var valid = true

        val email = input_email.text.toString()
        val password = input_password.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.error = getString(R.string.email_error)
            valid = false
        } else {
            input_email.error = null
        }

        if (password.isEmpty()) {
            input_password.error = getString(R.string.password_error)
            valid = false
        } else {
            input_password.error = null
        }

        return valid
    }

}
