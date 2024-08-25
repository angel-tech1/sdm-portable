package com.github.sdm.portable.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScaffoldTopBar(
  onSearch: (filter: String) -> Unit,
  onClear: () -> Unit,
  onTextChanges: (filters: String) -> Unit,
  onToggleConnectedOnly: () -> Unit,
  loadOnStartup: Boolean,
  connectedOnly: Boolean,
  filtersText: String,
  cliFailed: Boolean
) {

  var initializedState by remember { mutableStateOf(false) }

  if (loadOnStartup && !initializedState) {
    onSearch(filtersText)
    initializedState = true
  }

  Row(modifier = Modifier.fillMaxWidth()) {
    Column(modifier = Modifier.fillMaxWidth(0.8F)) {
      TextField(
        value = filtersText,
        enabled = !cliFailed,
        onValueChange = onTextChanges,
        label = { Text("Find by comma-separated keywords") },
        modifier = Modifier.onKeyEvent {
          onKeyEvent(it, onSearch, filtersText)
          true
        }.fillMaxWidth(1F),
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        singleLine = true,
        maxLines = 1
      )
    }

    Column(
      modifier = Modifier.fillMaxWidth(0.5F).background(Color.LightGray)
    ) {
      Row(modifier = Modifier.fillMaxWidth()) {
        Checkbox(
          checked = connectedOnly,
          onCheckedChange = { onToggleConnectedOnly() },
          modifier = Modifier.fillMaxWidth(0.25F).height(56.dp)
        )

        Text(
          "connected",
          modifier = Modifier.align(Alignment.CenterVertically),
        )
      }
    }

    Column(
      modifier = Modifier.fillMaxWidth(1F).background(Color.Gray)
    ) {
      Button(
        onClick = {
          onClear()
        },
        modifier = Modifier.fillMaxWidth(1F)
          .height(56.dp)
      ) {
        Text("Clear")
      }
    }
  }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun onKeyEvent(
  it: KeyEvent?,
  onSearch: (filter: String) -> Unit,
  filtersText: String?
) {
  if (it == null || filtersText == null)
    return // ignore

  if (it.key == Key.Enter) {
    onSearch(filtersText)
  }
}
