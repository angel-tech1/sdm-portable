import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.github.sdm.portable.composables.CliFailed
import com.github.sdm.portable.composables.RenderResourcesList
import com.github.sdm.portable.composables.ScaffoldBottomBar
import com.github.sdm.portable.composables.ScaffoldTopBar
import com.github.sdm.portable.domain.ResourcesTable
import com.github.sdm.portable.domain.filter
import com.github.sdm.portable.domain.toResourcesTable
import com.github.sdm.portable.os.runCommand
import java.nio.charset.StandardCharsets

@Composable
@Preview
fun App(testMode: Boolean? = false) {
    var resourcesTableState by remember { mutableStateOf(ResourcesTable(emptyList())) }
    var failedCommandState by remember { mutableStateOf(false) }
    var filtersState by remember { mutableStateOf("") }

    val onSearchGetStatusAndUpdateTable = { commaSeparatedFilters: String? ->
        val sdmStatus = if (testMode == true) {
            readTestingFile("/test/sdm_status.txt")
        } else {
            "sdm status".runCommand() ?: ""
        }
        failedCommandState = sdmStatus.isBlank() || sdmStatus.lowercase().contains("login again")
        resourcesTableState = sdmStatus.toResourcesTable().filter(commaSeparatedFilters ?: "")
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
                CliFailed()
            } else {
                RenderResourcesList(resourcesTableState, onSearchGetStatusAndUpdateTable, filtersState)
            }
        }
    }
}

fun readTestingFile(filePath: String): String {
    val inputStream = object {}::class.java.getResourceAsStream(filePath)
    return if (inputStream == null) "" else String(inputStream.readAllBytes(), StandardCharsets.UTF_8)
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
