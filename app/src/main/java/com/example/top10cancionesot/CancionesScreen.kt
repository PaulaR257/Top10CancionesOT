package com.example.top10cancionesot

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.top10cancionesot.datasource.CancionesRepository
import com.example.top10cancionesot.ui.theme.Top10CancionesOTTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CancionApp(context: Context) {
    val canciones = CancionesRepository().getCanciones()

    Scaffold(
        topBar = { CancionTopAppBar() }
    ) { contentPadding ->
        LazyColumn(contentPadding = contentPadding) {
            items(canciones.size) { index ->
                val cancion = canciones[index]
                CancionItem(
                    cancion = cancion,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                    context = context
                )
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CancionTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Logo a la izquierda
                LogoBarraSuperior()

                // Texto del título con la nueva fuente
                Text(
                    text = stringResource(R.string.titulo),
                    style = MaterialTheme.typography.bodyLarge

                )

                // Logo a la derecha
                LogoBarraSuperior()
            }
        },
        modifier = modifier
    )
}

/**
 * Método para mostrar el logo
 */
@Composable
fun LogoBarraSuperior(){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "logo",
        modifier = Modifier
            .padding(start = dimensionResource(id = R.dimen.logo_padding),end = dimensionResource(id = R.dimen.logo_padding))
            .size(dimensionResource(id = R.dimen.logo_size))
    )
}

@Composable
fun DescripcionCancion(
    @StringRes descripcionCancion: Int,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(descripcionCancion),
            style = MaterialTheme.typography.labelMedium
        )


    }
}

@Composable
fun CancionItem(
    cancion: com.example.top10cancionesot.model.Cancion,
    modifier: Modifier = Modifier,
    context: Context
) {
    var expanded by remember { mutableStateOf(false) }

    // Agregar un estado para el estado de reproducción del MediaPlayer
    var isPlaying by remember { mutableStateOf(false) }


    //Cambia el color de la tarjeta cuando se expande
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = "Cambio de color de fondo"
    )
    val mediaPlayer = remember { MediaPlayer.create(context, cancion.audioResourceId) }

    val playIcon = Icons.Filled.PlayArrow
    val pauseIcon = Icons.Filled.Pause

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color = color)
                .padding(dimensionResource(id = R.dimen.padding_small)),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CancionIcon(cancion.imageResourceId, expanded)
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                InformacionCancion(cancion.name)
                Spacer(modifier = Modifier.weight(1f))

                CancionItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                    songResourceId = cancion.audioResourceId
                )
            }

            if (expanded) {
                DescripcionCancion(
                    descripcionCancion = cancion.description,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))

                Text(
                    text = "Reproducir canción",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_small))
                )

                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))

                IconButton(onClick = {
                    // Lógica para reproducir
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    } else {
                        mediaPlayer.start()
                    }
                    // Actualizar el estado de reproducción después de hacer clic en el botón
                    isPlaying = mediaPlayer.isPlaying
                }) {
                    // Cambia el icono dependiendo del estado de reproducción
                    if(isPlaying){
                        Icon(
                            imageVector = pauseIcon,
                            contentDescription = "Play/Pause",
                        )
                    }else{
                        Icon(
                            imageVector =playIcon,
                            contentDescription = "Play/Pause",
                        )
                    }


                }
            }
        }
    }
}

@Composable
fun CancionIcon(
    @DrawableRes cancionIcon: Int,
    expanded: Boolean
) {
    // Crear una transición que animará el tamaño de la imagen
    val transition = updateTransition(targetState = expanded, label = "expandedTransition")

    // Definir una animación para el tamaño de la imagen
    val size by transition.animateDp(label = "sizeTransition") { isExpanded ->
        // Determina el tamaño de la imagen cuando se expande
        if (isExpanded) {
            dimensionResource(id = R.dimen.expanded_image_size) // Tamaño expandido
        } else {
            dimensionResource(id = R.dimen.image_size) // Tamaño normal
        }
    }

    Image(
        modifier = Modifier
            .size(size)
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(cancionIcon),
        contentDescription = null
    )
}


@Composable
private fun CancionItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    @RawRes songResourceId: Int,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}




@Composable
fun InformacionCancion(
    @StringRes nombreCancion: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(nombreCancion),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun CancionListPreview() {
    Top10CancionesOTTheme(darkTheme = false) {
        CancionApp(context = LocalContext.current)
    }
}
@Preview
@Composable
fun DarkCancionListPreview() {
    Top10CancionesOTTheme(darkTheme = true) {
        CancionApp(context = LocalContext.current)
    }
}




