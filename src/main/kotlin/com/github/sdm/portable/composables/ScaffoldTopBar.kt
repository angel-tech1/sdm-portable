package com.github.sdm.portable.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScaffoldTopBar(onClick: (filter: String) -> Unit, loadOnStartup: Boolean) {

    var text by remember { mutableStateOf("") }

    if (loadOnStartup) {
        onClick(text)
    }

    Row (modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Find by comma-separated keywords") },
            modifier = Modifier.fillMaxWidth()
                .onKeyEvent {
                    if (it.key == Key.Enter) {
                        onClick(text)
                        true
                    }
                    false
                },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true,
            maxLines = 1
        )
    }
}
