import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
  var connectedOnlyState by remember { mutableStateOf(false) }
  var sdmStatus by remember { mutableStateOf("") }

  val onFullReload = { commaSeparatedFilters: String? ->
    sdmStatus = if (testMode == true) {
      readTestingFile("/test/sdm_status.txt")
    } else {
      "sdm status".runCommand() ?: ""
    }
    failedCommandState = sdmStatus.isBlank() || sdmStatus.lowercase().contains("login again")
    val keywords = if (failedCommandState) "" else commaSeparatedFilters ?: ""
    resourcesTableState = sdmStatus.toResourcesTable().filter(keywords, connectedOnlyState)
  }

  val onSearchFilterResults = { commaSeparatedFilters: String? ->
    resourcesTableState = sdmStatus.toResourcesTable().filter(commaSeparatedFilters ?: "",
      connectedOnlyState)
  }

  val onClearFilters = {
    filtersState = ""
    resourcesTableState = sdmStatus.toResourcesTable().filter(filtersState, connectedOnlyState)
  }

  val onToggleConnectedOnly = {
    connectedOnlyState = !connectedOnlyState
    resourcesTableState = sdmStatus.toResourcesTable().filter(filtersState, connectedOnlyState)
  }

  MaterialTheme {
    Scaffold(
      topBar = {
        ScaffoldTopBar(
          onFullReload = onFullReload,
          onSearch = onSearchFilterResults,
          onClear = onClearFilters,
          onTextChanges = { filters -> filtersState = filters },
          loadOnStartup = true,
          filtersText = filtersState,
          cliFailed = failedCommandState,
          onToggleConnectedOnly = onToggleConnectedOnly,
          connectedOnly = connectedOnlyState
        )
      },
      bottomBar = {
        ScaffoldBottomBar(resourcesTableState.resources.size)
      }
    ) {
      if (failedCommandState) {
        CliFailed()
      } else {
        RenderResourcesList(resourcesTableState, onFullReload, filtersState)
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
    title = "SDMp",
    resizable = true,
    state = windowState
  ) {
    App()
  }
}
