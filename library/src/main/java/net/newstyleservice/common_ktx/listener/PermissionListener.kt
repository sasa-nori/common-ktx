package net.newstyleservice.common_ktx.listener

interface PermissionListener {
    fun onGranted()

    fun onDenied()
}