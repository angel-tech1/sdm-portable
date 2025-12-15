package com.github.sdm.portable.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun ScaffoldBottomBar(itemCount: Int) {
  Text(
    "\uD83D\uDD39 Resources found: $itemCount",
    modifier = Modifier
      .height(30.dp)
      .background(Color.Black)
      .fillMaxWidth()
      .padding(2.dp),
    fontSize = 0.9.em,
    color = Color.White
  )
}
