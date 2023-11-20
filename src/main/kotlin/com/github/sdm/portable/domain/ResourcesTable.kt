package com.github.sdm.portable.domain

data class ResourcesTable(
    var resources: List<String>
)

fun ResourcesTable.filter(filters: String): ResourcesTable {
    val splitFilters = filters.split(",")
    return ResourcesTable(resources.filter { resource -> splitFilters.all { resource.contains(it) } })
}

fun String.toResourcesTable(): ResourcesTable {
    if (this.isBlank()) {
        return ResourcesTable(emptyList())
    }

    return ResourcesTable(this.split('\n')
        .filter { it.isNotBlank() })
}
