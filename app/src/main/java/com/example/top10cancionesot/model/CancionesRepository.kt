package com.example.top10cancionesot.model

import com.example.top10cancionesot.R

class CancionesRepository {
    fun getCanciones(): List<Cancion> {
        return listOf(
            Cancion(
                imageResourceId = R.drawable.cancion1,
                name = R.string.cancion1,
                description = R.string.descripcion1,
                audioResourceId = R.raw.audio1
            ),
            Cancion(
                imageResourceId = R.drawable.cancion2,
                name = R.string.cancion2,
                description = R.string.descripcion2,
                audioResourceId = R.raw.audio2
            ),
            Cancion(
                imageResourceId = R.drawable.cancion3,
                name = R.string.cancion3,
                description = R.string.descripcion3,
                audioResourceId = R.raw.audio3
            ),
            Cancion(
                imageResourceId = R.drawable.cancion4,
                name = R.string.cancion4,
                description = R.string.descripcion4,
                audioResourceId = R.raw.audio4
            ),
            Cancion(
                imageResourceId = R.drawable.cancion5,
                name = R.string.cancion5,
                description = R.string.descripcion5,
                audioResourceId = R.raw.audio5
            ),
            Cancion(
                imageResourceId = R.drawable.cancion6,
                name = R.string.cancion6,
                description = R.string.descripcion6,
                audioResourceId = R.raw.audio6
            ),
            Cancion(
                imageResourceId = R.drawable.cancion7,
                name = R.string.cancion7,
                description = R.string.descripcion7,
                audioResourceId = R.raw.audio7
            ),
            Cancion(
                imageResourceId = R.drawable.cancion8,
                name = R.string.cancion8,
                description = R.string.descripcion8,
                audioResourceId = R.raw.audio8
            ),
            Cancion(
                imageResourceId = R.drawable.cancion9,
                name = R.string.cancion9,
                description = R.string.descripcion9,
                audioResourceId = R.raw.audio9
            ),
            Cancion(
                imageResourceId = R.drawable.cancion10,
                name = R.string.cancion10,
                description = R.string.descripcion10,
                audioResourceId = R.raw.audio10
            )
        )
    }
}
