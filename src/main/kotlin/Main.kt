import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.github.sdm.portable.composables.ScaffoldBottomBar
import com.github.sdm.portable.composables.ScaffoldTopBar
import com.github.sdm.portable.composables.SectionText
import com.github.sdm.portable.composables.ServiceItem
import com.github.sdm.portable.domain.ResourcesTable
import com.github.sdm.portable.domain.filter
import com.github.sdm.portable.domain.toResourcesTable
import com.github.sdm.portable.os.runCommand

@Composable
@Preview
fun App() {
    var resourcesTableState by remember { mutableStateOf(ResourcesTable(emptyList())) }
    var failedCommandState by remember { mutableStateOf(false) }
    var filtersState by remember { mutableStateOf("") }

    val onSearch = {
        filters: String ->
            val sdmStatus: String = "sdm status".runCommand() ?: ""
            if (sdmStatus.isBlank()) {
                failedCommandState = true
            }
            resourcesTableState = sdmStatus.toResourcesTable().filter(filters)
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                ScaffoldTopBar(
                    onSearch = onSearch,
                    onTextChanges = { filters ->
                        filtersState = filters
                    },
                    loadOnStartup = true,
                    filtersText = filtersState
                )

                if (failedCommandState) {
                    Text(
                        "Failed to run SDM command. Is the SDM-cli installed?",
                        color = Color.Red, modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            bottomBar = {
                ScaffoldBottomBar(resourcesTableState.resources.size)
            }
        ) {

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.fillMaxWidth(),
                userScrollEnabled = true
            ) {
                items(count = resourcesTableState.resources.size) { item ->
                    val line = resourcesTableState.resources[item]

                    Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {

                        if (line.contains("DATASOURCE")) {
                            SectionText("DATA SOURCES")
                        } else if (line.contains("SERVER")) {
                            SectionText("SERVERS")
                        } else {
                            ServiceItem(line, afterToggle = { onSearch(filtersState) })
                        }
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "StrongDM portable (unofficial)",
        resizable = true
    ) {
        App()
    }
}
