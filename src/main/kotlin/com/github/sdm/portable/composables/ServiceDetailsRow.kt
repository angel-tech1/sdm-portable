package com.github.sdm.portable.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
                .padding(top = 5.dp, bottom = 7.dp)
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

        Text(
            details
                .replace("|not|connected|", "")
                .replace("|connected|", ""),
            modifier = Modifier
                .padding(top = 5.dp, bottom = 7.dp)
                .fillMaxHeight()
                .fillMaxWidth()
                .pointerHoverIcon(PointerIcon.Hand, overrideDescendants = true)
                .onClick {
                    copyToClipboard(details, clipboardManager)
                },
            textAlign = TextAlign.Left,
            color = Color.Black,
            fontSize = 1.em
        )
    }
}

fun copyToClipboard(text: String?, clipboardManager: ClipboardManager) {
    clipboardManager.setText(AnnotatedString(text = "" + text))
    println("Copied text to clipboard: $text")
}