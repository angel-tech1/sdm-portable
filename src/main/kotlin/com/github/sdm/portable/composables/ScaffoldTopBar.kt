package com.github.sdm.portable.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScaffoldTopBar(onClick: (filter: String) -> Unit) {

    var text by remember { mutableStateOf("") }

    Row (modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Find by comma-separated keywords") },
            modifier = Modifier.fillMaxWidth(fraction = 0.7f),
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true,
            maxLines = 1
        )

        Button(
            onClick = { onClick(text) },
            modifier = Modifier.requiredHeight(55.dp)
                .requiredWidth(120.dp)
                .fillMaxWidth()
                .padding(5.dp)
                .align(Alignment.CenterVertically),
        ) {
            Text("Find")
        }
    }
}
