package com.johncoimbra.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.johncoimbra.netflixclone.adapter.FilmsAdapter
import com.johncoimbra.netflixclone.databinding.ActivityDetailsFilmsBinding
import com.johncoimbra.netflixclone.model.addFilms

class DetailsFilmsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsFilmsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsFilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        toolbar()

        val recycler_other_films = binding.recyclerOtherFilms
        recycler_other_films.adapter = FilmsAdapter(addFilms())
        recycler_other_films.layoutManager = GridLayoutManager(applicationContext, 3)
    }

    private fun toolbar(){
        val toolbar_details = binding.toolbarDetails
        toolbar_details.setNavigationIcon(R.drawable.ic_back)
        toolbar_details.setNavigationOnClickListener {
            startActivity(Intent(this, ListFilmsActivity::class.java))
            finish()
        }
    }
}