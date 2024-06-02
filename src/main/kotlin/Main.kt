import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.github.sdm.portable.composables.ScaffoldBottomBar
import com.github.sdm.portable.composables.ScaffoldTopBar
import com.github.sdm.portable.composables.SectionText
import com.github.sdm.portable.composables.ServiceItem
import com.github.sdm.portable.domain.ResourcesTable
import com.github.sdm.portable.domain.filter
import com.github.sdm.portable.domain.toResourcesTable
import com.github.sdm.portable.os.runCommand
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

@Composable
@Preview
fun App(testMode: Boolean? = false) {
    var resourcesTableState by remember { mutableStateOf(ResourcesTable(emptyList())) }
    var failedCommandState by remember { mutableStateOf(false) }
    var filtersState by remember { mutableStateOf("") }

    val onSearchGetStatusAndUpdateTable = { commaSeparatedFilters: String ->
        if (testMode == true) {
            val sdmStatus: String = readTestingFile("/test/sdm_status.txt")
            failedCommandState = sdmStatus.isBlank()
            resourcesTableState = sdmStatus.toResourcesTable().filter(commaSeparatedFilters)
        } else {
            val sdmStatus: String = "sdm status".runCommand() ?: ""
            failedCommandState = sdmStatus.isBlank()
            resourcesTableState = sdmStatus.toResourcesTable().filter(commaSeparatedFilters)
        }
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                ScaffoldTopBar(
                    onSearch = onSearchGetStatusAndUpdateTable,
                    onTextChanges = { filters -> filtersState = filters },
                    loadOnStartup = true,
                    filtersText = filtersState
                )
            },
            bottomBar = {
                ScaffoldBottomBar(resourcesTableState.resources.size)
            }
        ) {

            if (failedCommandState) {
                Text(
                    "Failed to run SDM command. Is the SDM-cli installed?",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .fillMaxHeight()
                )
            } else {
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
        }
    }
}

fun readTestingFile(filePath: String): String {
    val inputStream = object {}::class.java.getResourceAsStream(filePath)
    return if (inputStream == null) "" else String(inputStream.readAllBytes(), StandardCharsets.UTF_8)
}

// Known SDM sections
val sections = listOf("DATASOURCE", "WEBSITE", "SERVER")

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

fun main() = application {
    val windowState = rememberWindowState(
        placement = WindowPlacement.Floating,
        width = 1280.dp,
        height = 800.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "StrongDM portable (unofficial)",
        resizable = true,
        state = windowState
    ) {
        App(true)
    }
}
