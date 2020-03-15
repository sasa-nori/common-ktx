package net.newstyleservice.common_ktx.extension

import java.io.File

/**
 * Recursive Delete Files
 */
fun File.recursiveDeleteFile() {
    if (!exists()) return

    if (isDirectory) {
        listFiles()?.forEach {
            it.recursiveDeleteFile()
        }
    }

    delete()
}
