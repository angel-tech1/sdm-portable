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
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent

@Composable
fun ScaffoldTopBar(
    onSearch: (filter: String) -> Unit,
    onTextChanges: (filters: String) -> Unit,
    loadOnStartup: Boolean,
    filtersText: String
) {

    var initializedState by remember { mutableStateOf(false) }

    if (loadOnStartup && !initializedState) {
        onSearch(filtersText)
        initializedState = true
    }

    Row (modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = filtersText,
            onValueChange = onTextChanges,
            label = { Text("Find by comma-separated keywords") },
            modifier = Modifier.fillMaxWidth()
                .onKeyEvent {
                    onKeyEvent(it, onSearch, filtersText)
                    true
                },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true,
            maxLines = 1
        )
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
