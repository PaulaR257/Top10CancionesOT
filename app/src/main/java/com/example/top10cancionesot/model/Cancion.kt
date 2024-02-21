package com.example.top10cancionesot.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class Cancion(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @RawRes val audioResourceId: Int // Nuevo campo para el ID del recurso de audio

)