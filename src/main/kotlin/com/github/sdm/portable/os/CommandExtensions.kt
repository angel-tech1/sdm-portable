package com.github.sdm.portable.os

import java.io.File
import java.util.concurrent.TimeUnit

fun String.runCommand(
    workingDir: File = File("."),
    timeoutAmount: Long = 60,
    timeoutUnit: TimeUnit = TimeUnit.SECONDS,
    afterCommand: () -> Unit = {}
): String? {
    println("Command: $this")
    return runCatching {
        ProcessBuilder("\\s".toRegex().split(this))
            .directory(workingDir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start().also { it.waitFor(timeoutAmount, timeoutUnit) }.also { afterCommand() }
            .inputStream.bufferedReader().readText()
    }.onFailure { it.printStackTrace() }.getOrNull()
}
