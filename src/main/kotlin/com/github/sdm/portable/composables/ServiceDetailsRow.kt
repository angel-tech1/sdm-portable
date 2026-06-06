package com.github.sdm.portable.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ServiceDetailsRow(text: String) {
  Row(
    modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)
      .fillMaxWidth(fraction = 0.80f)
  ) {
    val serviceId = text.substring(0, text.indexOf('|'))
    val details = text.substring(text.indexOf('|'))

    val clipboardManager = LocalClipboardManager.current

    Text(
      serviceId,
      modifier = Modifier
        .padding(top = 5.dp, bottom = 7.dp, start = 15.dp)
        .fillMaxHeight()
        .fillMaxWidth(fraction = 0.35f)
        .clickable {
          clipboardManager.setText(AnnotatedString(serviceId))
          println("Copied text to clipboard: $serviceId")
        },
      textAlign = TextAlign.Left,
      color = Color.Black,
      fontSize = 1.em,
      fontWeight = FontWeight.Bold,
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
