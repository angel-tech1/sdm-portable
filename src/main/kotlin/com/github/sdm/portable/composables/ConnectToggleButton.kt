package com.github.sdm.portable.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun ConnectToggleButton(disconnected: Boolean) {
    Button(
        enabled = true,
        modifier = Modifier
            .requiredHeight(35.dp)
            .paddingFromBaseline(top = 15.dp)
            .fillMaxWidth(),
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (disconnected) Color.Red else Color.Green,
            contentColor = Color.White
        )
    ) {
        Text(
            if (disconnected) "Disconnected" else "Connected",
            fontSize = 1.em
        )
    }
}
