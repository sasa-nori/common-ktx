package ss_n.common_ktx

import android.Manifest
import androidx.annotation.RequiresApi

enum class Permissions(val permissions: Array<String>) {
    /**
     * [Manifest.permission.READ_MEDIA_IMAGES]
     */
    @RequiresApi(33)
    IMAGE_READ(arrayOf(Manifest.permission.READ_MEDIA_IMAGES)),

    /**
     * [Manifest.permission.READ_MEDIA_VIDEO]
     */
    @RequiresApi(33)
    MOVIE_READ(arrayOf(Manifest.permission.READ_MEDIA_VIDEO)),

    /**
     * [Manifest.permission.READ_MEDIA_AUDIO]
     */
    @RequiresApi(33)
    AUDIO_READ(arrayOf(Manifest.permission.READ_MEDIA_AUDIO)),

    /**
     * [Manifest.permission.READ_MEDIA_IMAGES]
     * [Manifest.permission.READ_MEDIA_VIDEO]
     * [Manifest.permission.READ_MEDIA_AUDIO]
     */
    @RequiresApi(33)
    READ_MEDIA(
        arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO
        )
    ),

    /**
     * [Manifest.permission.READ_EXTERNAL_STORAGE]
     * [Manifest.permission.WRITE_EXTERNAL_STORAGE]
     */
    FILE(
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    ),

    /**
     * [Manifest.permission.ACCESS_FINE_LOCATION]
     * [Manifest.permission.ACCESS_COARSE_LOCATION]
     */
    LOCATION(
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    ),

    /**
     * [Manifest.permission.CAMERA]
     * [Manifest.permission.RECORD_AUDIO]
     * [Manifest.permission.WRITE_EXTERNAL_STORAGE]
     * [Manifest.permission.READ_EXTERNAL_STORAGE]
     */
    MEDIA_RECORD(
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    ),

    /**
     * [Manifest.permission.CAMERA]
     */
    CAMERA(arrayOf(Manifest.permission.CAMERA)),

    /**
     * [Manifest.permission.CAMERA]
     * [Manifest.permission.WRITE_EXTERNAL_STORAGE]
     * [Manifest.permission.READ_EXTERNAL_STORAGE]
     */
    CAMERA_CAPTURE(
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    ),

    /**
     * [Manifest.permission.RECORD_AUDIO]
     * [Manifest.permission.WRITE_EXTERNAL_STORAGE]
     * [Manifest.permission.READ_EXTERNAL_STORAGE]
     */
    AUDIO_RECORD(
        arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    ),

    /**
     * [Manifest.permission.CAMERA]
     */
    MIC(arrayOf(Manifest.permission.RECORD_AUDIO)),

    /**
     * [Manifest.permission.READ_CALENDAR]
     * [Manifest.permission.WRITE_CALENDAR]
     */
    CALENDER(
        arrayOf(
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
        )
    ),

    /**
     * [Manifest.permission.POST_NOTIFICATIONS]
     */
    @RequiresApi(33)
    NOTIFICATION(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
}
