package ss_n.common_ktx.extension

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Criteria
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build.VERSION_CODES
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import ss_n.common_ktx.Permissions
import ss_n.common_ktx.SoundPool
import kotlin.coroutines.suspendCoroutine

fun Context.hasRuntimePermissions(permissions: Array<String>): Boolean {
    if (permissions.isEmpty()) return false

    return permissions.none {
        ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
    }
}

fun Context.hasFilePermission(): Boolean =
    hasRuntimePermissions(Permissions.FILE.permissions)

fun Context.hasLocationPermission(): Boolean =
    hasRuntimePermissions(Permissions.LOCATION.permissions)

fun Context.hasMediaRecordPermission(): Boolean =
    hasRuntimePermissions(Permissions.MEDIA_RECORD.permissions)

fun Context.hasCameraPermission(): Boolean =
    hasRuntimePermissions(Permissions.CAMERA.permissions)

fun Context.hasCameraCapturePermission(): Boolean =
    hasRuntimePermissions(Permissions.CAMERA_CAPTURE.permissions)

fun Context.hasAudioRecordPermission(): Boolean =
    hasRuntimePermissions(Permissions.AUDIO_RECORD.permissions)

fun Context.hasMicPermission(): Boolean =
    hasRuntimePermissions(Permissions.MIC.permissions)

fun Context.hasCalendarPermission(): Boolean =
    hasRuntimePermissions(Permissions.CALENDER.permissions)

fun Context.getPreferences(name: String = "shared_pref"): SharedPreferences =
    getSharedPreferences(name, Context.MODE_PRIVATE)

fun Context.changeDpToPx(dp: Float): Float = dp * resources.displayMetrics.density

fun Context.getColorCompat(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

fun Context.getLocationManager(): LocationManager = getSystemService()!!

fun Context.getAlarmManager(): AlarmManager = getSystemService()!!

fun Context.getConnectivityManager(): ConnectivityManager = getSystemService()!!

fun Context.getInputMethodManager(): InputMethodManager = getSystemService()!!

fun Context.getNotificationManager(): NotificationManager = getSystemService()!!

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isActiveNetwork(): Boolean {
    getConnectivityManager().let {
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
    }
}

/**
 * Hide to Keyboard
 */
fun Context.hideKeyboard(view: View) {
    getInputMethodManager().hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}

@RequiresApi(api = VERSION_CODES.O)
fun Context.setNotificationChannel(
    channelId: String,
    name: String,
    priority: Int = NotificationManager.IMPORTANCE_HIGH,
    description: String? = null
) {
    val notificationManager = getNotificationManager()
    if (notificationManager.getNotificationChannel(channelId) != null) return

    val notificationChannel =
        NotificationChannel(channelId, name, priority)
    description?.let { notificationChannel.description = it }
    notificationManager.createNotificationChannel(notificationChannel)
}

fun Context.sendNotification(
    tag: String? = null,
    notificationId: Int,
    channelId: String,
    sender: PendingIntent,
    title: String,
    message: String,
    @DrawableRes smallIcon: Int,
    style: NotificationCompat.Style? = null,
    largeIcon: Bitmap? = null,
    largeIconRes: Int? = null,
    isBigStyle: Boolean = false,
    isAutoCancel: Boolean = false,
    isFullScreenIntent: Boolean = false
) {
    val notification = NotificationCompat.Builder(this, channelId).apply {
        setContentTitle(title)
        setContentText(message)
        setSmallIcon(smallIcon)
        style?.let { setStyle(style) }
        if (isBigStyle) {
            setStyle(NotificationCompat.BigTextStyle().bigText(message))
        }
        largeIcon?.let { setLargeIcon(largeIcon) }
        largeIconRes?.let { setLargeIcon(BitmapFactory.decodeResource(resources, largeIconRes)) }
        if (isFullScreenIntent) {
            setFullScreenIntent(sender, true)
        }
        setAutoCancel(isAutoCancel)
        setContentIntent(sender)
    }.build()

    getNotificationManager().notify(tag, notificationId, notification)
}

@SuppressLint("MissingPermission")
fun Context.requestLocation(
    locationListener: LocationListener,
    permissionError: (() -> Unit?)? = null,
    criteria: Criteria = Criteria().apply
    {
        accuracy = Criteria.ACCURACY_FINE
        powerRequirement = Criteria.POWER_HIGH
        isSpeedRequired = false
        isAltitudeRequired = false
        isBearingRequired = false
        isCostAllowed = true
        horizontalAccuracy = Criteria.ACCURACY_HIGH
        verticalAccuracy = Criteria.ACCURACY_HIGH
    },
    enabledOnly: Boolean = true,
    minTime: Long = 1000,
    minDistance: Float = 40f
) {
    if (!hasLocationPermission()) {
        permissionError?.let { it() }
        return
    }
    val locationManager = getLocationManager()
    val provider = locationManager.getBestProvider(criteria, enabledOnly)
    provider?.let {
        locationManager.requestLocationUpdates(it, minTime, minDistance, locationListener)
    }
}

suspend fun Context.loadSoundPool(files: MutableList<Int>): MutableList<Int> {
    return suspendCoroutine {
        SoundPool.init(this, files) { list ->
            it.resumeWith(Result.success(list))
        }
    }
}