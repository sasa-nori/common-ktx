package app.ss_n.common_ktx.extension

import android.Manifest
import androidx.annotation.RequiresPermission
import java.io.File

/**
 * Recursive Delete Files
 */
@RequiresPermission(
    allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE]
)
fun File.recursiveDeleteFile() {
    if (!exists()) return

    if (isDirectory) {
        listFiles()?.forEach {
            it.recursiveDeleteFile()
        }
    }

    delete()
}
