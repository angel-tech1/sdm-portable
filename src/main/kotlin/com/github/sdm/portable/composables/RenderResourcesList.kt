package com.github.sdm.portable.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.sdm.portable.domain.ResourcesTable

// Known SDM sections
val sections = listOf("DATASOURCE", "WEBSITE", "SERVER", "CLOUD")

@Composable
fun RenderResourcesList(
        resourcesTableState: ResourcesTable,
        onSearchGetStatusAndUpdateTable: (String) -> Unit,
        filtersState: String
    ) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .fillMaxHeight(),
        userScrollEnabled = true
    ) {
        items(count = resourcesTableState.resources.size) { item ->
            val sdmStatusLine = resourcesTableState.resources[item]

            Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
                renderSdmStatusLine(
                    sdmStatusLine,
                    afterToggle = onSearchGetStatusAndUpdateTable,
                    commaSeparatedFilters = filtersState
                )
            }
        }
    }
}

@Composable
fun renderSdmStatusLine(
    sdmStatusLine: String,
    afterToggle: (String) -> Unit,
    commaSeparatedFilters: String
) {

    if (sections.any { s -> sdmStatusLine.contains(s) }) {
        SectionText(sdmStatusLine)
    } else {
        ServiceItem(
            sdmStatusLine,
            afterToggle = { afterToggle(commaSeparatedFilters) }
        )
    }
}
