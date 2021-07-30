package com.johncoimbra.netflixclone.model

import com.johncoimbra.netflixclone.R

data class Films(
    val movieCover: Int
)

class FilmsBuilder {
    var movieCover: Int = 0
    fun build(): Films = Films(movieCover)
}

fun films(block: FilmsBuilder.() -> Unit): Films = FilmsBuilder().apply(block).build()

fun addFilms(): MutableList<Films> = mutableListOf(
    films {
        movieCover = R.drawable.filme1
    },

    films {
        movieCover = R.drawable.filme2
    },

    films {
        movieCover = R.drawable.filme3
    },
    films {
        movieCover = R.drawable.filme4
    },

    films {
        movieCover = R.drawable.filme5
    },

    films {
        movieCover = R.drawable.filme6
    },
    films {
        movieCover = R.drawable.filme7
    },

    films {
        movieCover = R.drawable.filme8
    },

    films {
        movieCover = R.drawable.filme9
    },
    films {
        movieCover = R.drawable.filme10
    },

    films {
        movieCover = R.drawable.filme11
    },

    films {
        movieCover = R.drawable.filme12
    },
)
