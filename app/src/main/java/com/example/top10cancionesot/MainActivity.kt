package com.example.top10cancionesot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.top10cancionesot.ui.theme.Top10CancionesOTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Top10CancionesOTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CancionApp(this)
                }
            }
        }
    }
}


@Preview
@Composable
fun CancionPreview() {
    Top10CancionesOTTheme(darkTheme = false) {
        CancionApp(context = LocalContext.current)
    }
}

@Preview
@Composable
fun DarkCancionPreview() {
    Top10CancionesOTTheme (darkTheme = true){
        CancionApp(context = LocalContext.current)
    }
}
