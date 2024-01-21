package com.example.amberd.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.amberd.Databases.DbHelper
import com.example.amberd.R
import com.example.amberd.Models.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPassword: EditText = findViewById(R.id.user_password)
        val button: Button = findViewById(R.id.button_reg)
        val linkToAuth: TextView = findViewById(R.id.link_to_auth)

        linkToAuth.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login == "" || email == "" || password == "") {
                Toast.makeText(this, "Fill in all the gaps!", Toast.LENGTH_LONG).show()
            } else {
                val user = User(login, email, password)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "The user was added!", Toast.LENGTH_SHORT).show()

                userEmail.text.clear()
                userLogin.text.clear()
                userPassword.text.clear()

            }
        }
    }

}