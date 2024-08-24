package com.github.sdm.portable.domain

import com.github.sdm.portable.composables.renderSdmStatusLine

data class ResourcesTable(
    var resources: List<String>
)

fun ResourcesTable.filter(filters: String, connectedOnly: Boolean): ResourcesTable {
    val splitFilters = filters.split(",")
    val resourcesToFilter = if (connectedOnly) {
        resources.filter { resource -> splitFilters.all { !resource.lowercase().contains("not|connected") }}
    } else resources

    val resourcesFiltered = resourcesToFilter.filter { resource ->
        splitFilters.all {
            resource.lowercase().contains(it.lowercase())
        }
    }
    return ResourcesTable(resourcesFiltered)
}

fun String.toResourcesTable(): ResourcesTable {
    if (this.isBlank()) {
        return ResourcesTable(emptyList())
    }

    return ResourcesTable(this.split('\n')
        .filter { it.isNotBlank() }.map { it.trim().replace("\\t+".toRegex(), " ")
            .replace("\\s+".toRegex(), "|") })
}
