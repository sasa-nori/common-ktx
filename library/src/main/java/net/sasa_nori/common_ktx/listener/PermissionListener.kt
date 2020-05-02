package net.sasa_nori.common_ktx.listener

/**
 * Runtime Permissions Check Listener
 */
interface PermissionListener {
    /**
     * Runtime Permissions Granted
     */
    fun onGranted()

    /**
     * Runtime Permissions Denied
     */
    fun onDenied()
}
