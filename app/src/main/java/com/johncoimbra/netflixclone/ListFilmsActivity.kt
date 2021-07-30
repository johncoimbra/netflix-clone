package com.johncoimbra.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.johncoimbra.netflixclone.adapter.FilmsAdapter
import com.johncoimbra.netflixclone.databinding.ActivityListFilmsBinding
import com.johncoimbra.netflixclone.databinding.ActivityRegisterBinding
import com.johncoimbra.netflixclone.model.addFilms
import com.johncoimbra.netflixclone.onclick.OnItemClickListener
import com.johncoimbra.netflixclone.onclick.addOnItemClickListener

class ListFilmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListFilmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListFilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler_films = binding.recyclerView
        recycler_films.adapter = FilmsAdapter(addFilms())
        recycler_films.layoutManager = GridLayoutManager(applicationContext, 3)

        recycler_films.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                when{
                    position == 0 -> goDetailsFilms()
                }
            }

        })
    }

    private fun goDetailsFilms(){
        startActivity(Intent(this, DetailsFilmsActivity::class.java))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logoff -> {
                FirebaseAuth.getInstance().signOut()
                backLoginActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun backLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}