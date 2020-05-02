package net.sasa_nori.common_ktx.extension

import android.content.pm.PackageManager
import net.sasa_nori.common_ktx.listener.PermissionListener

/**
 * Check Runtime Permissions
 *
 * @param permissionListener [PermissionListener]
 */
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

/**
 * Check Runtime Permissions Result
 *
 * @return true: All Permissions is Granted
 */
fun IntArray.isSuccessPermissionResult(): Boolean {
    if (isEmpty()) return false

    return findLast { it == PackageManager.PERMISSION_DENIED }?.let { false } ?: run { true }
}
