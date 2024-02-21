/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.top10cancionesot.data

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.top10cancionesot.R


/**
 * A data class to represent the information presented in the dog card
 */
data class Cancion(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @RawRes val audioResourceId: Int // Nuevo campo para el ID del recurso de audio
)


val canciones = listOf(
    Cancion(R.drawable.cancion1, R.string.cancion1,  R.string.descripcion1,R.raw.audio1),
    Cancion(R.drawable.cancion2, R.string.cancion2,  R.string.descripcion2,R.raw.audio2),
    Cancion(R.drawable.cancion3, R.string.cancion3,  R.string.descripcion3,R.raw.audio3),
    Cancion(R.drawable.cancion4, R.string.cancion4,  R.string.descripcion4,R.raw.audio4),
    Cancion(R.drawable.cancion5, R.string.cancion5,  R.string.descripcion5,R.raw.audio5),
    Cancion(R.drawable.cancion6, R.string.cancion6,  R.string.descripcion6,R.raw.audio6),
    Cancion(R.drawable.cancion7, R.string.cancion7,  R.string.descripcion7,R.raw.audio7),
    Cancion(R.drawable.cancion8, R.string.cancion8,  R.string.descripcion8,R.raw.audio8),
    Cancion(R.drawable.cancion9, R.string.cancion9,  R.string.descripcion9,R.raw.audio9),
    Cancion(R.drawable.cancion10, R.string.cancion10,  R.string.descripcion10,R.raw.audio10),

)
