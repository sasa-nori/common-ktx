package net.newstyleservice.common_ktx.extension

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService

fun Context.hasRuntimePermissions(permissions: List<String>): Boolean {
    if (permissions.isEmpty()) return false

    return permissions.none {
        ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
    }
}

fun Context.getPreferences(name: String = "shared_pref"): SharedPreferences =
    getSharedPreferences(name, Context.MODE_PRIVATE)

fun Context.changeDpToPx(dp: Float): Float = dp * resources.displayMetrics.density

fun Context.getColorCompat(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

fun Context.getLocationManager(): LocationManager? = getSystemService()

fun Context.getAlarmManager(): AlarmManager? = getSystemService()

fun Context.getConnectivityManager(): ConnectivityManager? = getSystemService()

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isActiveNetwork(): Boolean {
    val connectivityManager = getConnectivityManager()
    return connectivityManager?.let {
        val nw = it.activeNetwork ?: return false
        val actNw = it.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            // for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            // for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } ?: false
}

/**
 * Hide to Keyboard
 */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager: InputMethodManager = getSystemService()!!
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}
