package com.github.sdm.portable.domain

data class ResourcesTable(
    var resources: List<String>
)

fun ResourcesTable.filter(filters: String): ResourcesTable {
    return ResourcesTable(resources.filter { it.contains(filters) })
}

fun String.toResourcesTable(): ResourcesTable {
    if (this.isBlank()) {
        return ResourcesTable(emptyList())
    }

    return ResourcesTable(this.split('\n')
        .filter { it.isNotBlank() })
}
