package com.github.sdm.portable.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun ServiceDetailsRow(text: String) {
    Row(modifier = Modifier.padding(2.dp)) {
        Text(
            text,
            modifier = Modifier
                .padding(top = 5.dp, bottom = 7.dp)
                .fillMaxHeight()
                .fillMaxWidth(fraction = 0.80f),
            textAlign = TextAlign.Left,
            color = Color.Black,
            fontSize = 1.em
        )
    }
}
