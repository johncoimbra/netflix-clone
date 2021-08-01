package com.johncoimbra.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.johncoimbra.netflixclone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        verifyUserLogged()

        binding.textSubscribe.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val message_error = binding.textMessageError

            if (email.isEmpty() || password.isEmpty()) {
                message_error.setText("Preencha todos os campos!")
            } else {
                autenticationUser()
            }
        }
    }

    private fun autenticationUser() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val message_error = binding.textMessageError

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                    goListFilms()
                    finish()
                }
            }.addOnFailureListener {
            var error = it
            when {
                error is FirebaseAuthInvalidCredentialsException -> message_error.setText("E-mail ou Senha estão incorretos!")
                error is FirebaseNetworkException -> message_error.setText("Sem conexão com a internet!")
                else -> message_error.setText("Erro ao logar usuário!")
            }
        }
    }

    private fun verifyUserLogged(){
        val userLogged = FirebaseAuth.getInstance().currentUser
        if(userLogged != null){
            goListFilms()
        }
    }

    private fun goListFilms(){
        startActivity(Intent(this, ListFilmsActivity::class.java))
    }
}