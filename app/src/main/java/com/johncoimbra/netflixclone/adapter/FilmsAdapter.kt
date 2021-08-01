package com.johncoimbra.netflixclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johncoimbra.netflixclone.databinding.ListFilmsBinding
import com.johncoimbra.netflixclone.model.Films

class FilmsAdapter(val films: MutableList<Films>): RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val binding = ListFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        with(holder){
            with(films[position]){
                binding.movieCover.setImageResource(movieCover)
            }
        }
    }

    override fun getItemCount() = films.size

    inner class FilmsViewHolder(val binding: ListFilmsBinding): RecyclerView.ViewHolder(binding.root)
}