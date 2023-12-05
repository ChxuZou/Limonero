package com.example.limonero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonApp()
        }
    }
}

@Composable
fun LemonApp() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BarraInicial()
        ImagenesYTexto()
    }
}

@Composable
fun BarraInicial(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1F)
            .background(Color.Yellow),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(25.dp)
        )
    }
}

@Composable
fun ImagenesYTexto() {
    var numImagenYTexto by remember { mutableStateOf(1) }
    var esLimon = false
    var contadorClicksLimon by remember { mutableStateOf(0) }
    var randomNum: Int by remember { mutableStateOf((1..3).random()) }

    //Condición de reset
    if (numImagenYTexto > 4) {
        numImagenYTexto = 1
        contadorClicksLimon = 0
        randomNum = (1..3).random()
    }

    //Condición de bucle
    if (numImagenYTexto == 2) {
        esLimon = contadorClicksLimon != randomNum
    }

    //Asignación de imagen
    val imagen = when (numImagenYTexto) {
        1 -> R.drawable.limonero
        2 -> R.drawable.limon
        3 -> R.drawable.zumo_limon
        else -> R.drawable.vaso_vacio
    }

    //Asignación de texto
    val texto = when (numImagenYTexto) {
        1 -> stringResource(R.string.limonero)
        2 -> stringResource(R.string.limon)
        3 -> stringResource(R.string.zumo_limon)
        else -> stringResource(R.string.vaso_vacio)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (!esLimon) {
                    numImagenYTexto++
                } else {
                    contadorClicksLimon++
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(all = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan
            )

        ) {
            Image(
                painter = painterResource(imagen),
                contentDescription = null
            )
        }
        Text(
            text = texto,
            fontWeight = FontWeight.SemiBold
        )


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonApp()
}