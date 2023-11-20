package com.github.sdm.portable.composables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun ServiceDetailsRow(text: String) {
    Text(
        text,
        modifier = Modifier
            .padding(top = 5.dp, bottom = 7.dp)
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.85f),
        textAlign = TextAlign.Left,
        fontSize = 1.em
    )
}
