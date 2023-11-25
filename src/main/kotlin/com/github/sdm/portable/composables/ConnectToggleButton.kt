package com.github.sdm.portable.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.github.sdm.portable.os.runCommand

@Composable
fun ConnectToggleButton(
    disconnected: Boolean,
    serviceId: String,
    afterToggle: () -> Unit
) {
    Button(
        enabled = true,
        modifier = Modifier
            .requiredHeight(32.dp)
            .fillMaxWidth(),
        onClick = {
            val command = if (disconnected) "connect" else "disconnect"
            "sdm $command $serviceId".runCommand(timeoutAmount = 5, afterCommand = afterToggle)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (disconnected) Color.Red else Color.Green,
            contentColor = if (disconnected) Color.White else Color.Black
        )
    ) {
        Text(
            if (disconnected) "Disconnected" else "Connected",
            fontSize = 0.9.em,
            fontWeight = FontWeight.Bold
        )
    }
}
