package com.github.sdm.portable.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun ScaffoldBottomBar(itemCount: Int) {
    Text(
        "Resources found: $itemCount",
        modifier = Modifier.height(30.dp).background(Color.Black).fillMaxWidth(),
        fontSize = 0.7.em,
        color = Color.White
    )
}
