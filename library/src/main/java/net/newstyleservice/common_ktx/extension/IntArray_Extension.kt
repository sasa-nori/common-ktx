package net.newstyleservice.common_ktx.extension

import android.content.pm.PackageManager
import net.newstyleservice.common_ktx.listener.PermissionListener

fun IntArray.onPermissionResult(permissionListener: PermissionListener) {
    if (isEmpty()) {
        permissionListener.onDenied()
        return
    }
    findLast { it == PackageManager.PERMISSION_DENIED }?.let {
        permissionListener.onDenied()
    } ?: run {
        permissionListener.onGranted()
    }
}

fun IntArray.isSuccessPermissionResult(): Boolean {
    if (isEmpty()) return false

    return findLast { it == PackageManager.PERMISSION_DENIED }?.let { false } ?: run { true }
}
