package com.johncoimbra.netflixclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.johncoimbra.netflixclone.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        toolbar()

        binding.btRegister.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val message_error = binding.textMessageError

            if (email.isEmpty() || password.isEmpty()) {
                message_error.setText("Preencha todos os campos")
            } else {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val message_error = binding.textMessageError

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    binding.editEmail.setText("")
                    binding.editPassword.setText("")
                    message_error.setText("")
                }
            }.addOnFailureListener {

                var error = it

                when {
                    error is FirebaseAuthWeakPasswordException -> message_error.setText("Digite uma senha com no mínimo 6 caracteres!")
                    error is FirebaseAuthUserCollisionException -> message_error.setText("Esta conta já está cadastrada!")
                    error is FirebaseNetworkException -> message_error.setText("Sem conexão com a internet")
                    else -> message_error.setText("Erro ao cadastrar usuário!")
                }
            }
    }

    private fun toolbar() {
        val toolbar = binding.toolbarRegister
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_netflix_official_logo))
    }
}