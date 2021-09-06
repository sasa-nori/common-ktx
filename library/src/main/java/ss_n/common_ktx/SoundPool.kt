package ss_n.common_ktx

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

/**
 * SoundPool [android.media.SoundPool] Wrapper Class
 */
object SoundPool {
    /** Memory cache size */
    private const val MAX_STREAM_SIZE = 256

    /** [android.media.SoundPool] */
    private var soundPool: SoundPool? = null

    /** SoundPool loaded file list */
    private val playList: MutableList<Int> = mutableListOf()

    /**
     * example is [ss_n.common_ktx.extension.loadSoundPool]
     */
    fun init(
        context: Context,
        files: List<Int>,
        usage: Int = AudioAttributes.USAGE_GAME,
        contentType: Int = AudioAttributes.CONTENT_TYPE_SPEECH,
        listener: (MutableList<Int>) -> Unit
    ) {
        if (files.size > MAX_STREAM_SIZE) {
            throw RuntimeException("oops. list is limit over")
        }

        soundPool?.let {
            playList.forEach {
                soundPool?.unload(it)
            }
        }

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(usage)
            .setContentType(contentType)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(MAX_STREAM_SIZE)
            .build()

        playList.clear()

        soundPool?.setOnLoadCompleteListener { _, _, _ ->
            if (files.size == playList.size) {
                listener.invoke(playList)
            }
        }

        files.forEach { file ->
            soundPool?.let {
                playList.add(it.load(context, file, 1))
            }
        }
    }

    /**
     * If you specify the loaded path in init(), the sound will be played.
     */
    fun play(
        playId: Int,
        left: Float = 1.0f,
        right: Float = 1.0f,
        priority: Int = 0,
        loop: Int = 0,
        rate: Float = 1.0f
    ) {
        soundPool?.play(playId, left, right, priority, loop, rate)
    }

    /**
     * memory release
     *
     * Forgetting to use this process will cause a memory leak.
     */
    fun release() {
        soundPool?.release()
    }
}
