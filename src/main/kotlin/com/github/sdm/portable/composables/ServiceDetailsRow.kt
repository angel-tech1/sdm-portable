package com.github.sdm.portable.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import javax.swing.Icon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServiceDetailsRow(text: String) {
  Row(
    modifier = Modifier.padding(2.dp)
      .fillMaxWidth(fraction = 0.80f)
  ) {
    val serviceId = text.substring(0, text.indexOf('|'))
    val details = text.substring(text.indexOf('|'))

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Text(
      serviceId,
      modifier = Modifier
        .padding(top = 5.dp, bottom = 7.dp, start = 15.dp)
        .fillMaxHeight()
        .fillMaxWidth(fraction = 0.35f)
        .pointerHoverIcon(PointerIcon.Hand, overrideDescendants = true)
        .onClick {
          copyToClipboard(serviceId, clipboardManager)
        },
      textAlign = TextAlign.Left,
      color = Color.Black,
      fontSize = 1.em,
      fontWeight = FontWeight.Bold
    )

    // The second column will contain multiple individual columns
    // The number of columns generated for each service could differ (non-deterministic)
    Row(
      horizontalArrangement = Arrangement.Start,
      modifier = Modifier
        .padding(top = 5.dp, bottom = 7.dp)
        .fillMaxHeight()
        .fillMaxWidth()
        .align(Alignment.CenterVertically)
    ) {

      // Render each one of the pipe-separated values as individual text components
      details.replace("|not|connected|", "")
        .replace("|connected|", "")
        .split("|")
        .filter { it.isNotBlank() }.forEach { columnValue ->

          Text(
            columnValue,
            modifier = Modifier.defaultMinSize(minWidth = 50.dp)
              .padding(end = 10.dp, start = 5.dp),
            textAlign = TextAlign.Left,
            color = Color.Black,
            fontSize = 1.1.em
          )
      }
    }
  }
}

fun copyToClipboard(text: String?, clipboardManager: ClipboardManager) {
  clipboardManager.setText(AnnotatedString(text = "" + text))
  println("Copied text to clipboard: $text")
}