package net.newstyleservice.common_ktx

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.text.TextUtils
import net.newstyleservice.common_ktx.TextToSpeechManager.Error.MESSAGE_EMPTY
import net.newstyleservice.common_ktx.TextToSpeechManager.Error.NONE
import net.newstyleservice.common_ktx.TextToSpeechManager.Error.NOT_CONTEXT
import net.newstyleservice.common_ktx.TextToSpeechManager.Error.UNKNOWN
import net.newstyleservice.common_ktx.extension.hasJapanese
import java.util.Locale

object TextToSpeechManager {
    var context: Context? = null

    private var status: Status = Status.NONE

    private val textToSpeech: TextToSpeech?
        get() = context?.let {
            TextToSpeech(context,
                TextToSpeech.OnInitListener { response: Int ->
                    if (TextToSpeech.SUCCESS == response) {
                        if (hasJapanese) {
                            textToSpeech?.language = Locale.JAPAN
                            status = Status.SUCCESS
                            return@OnInitListener
                        }
                        status = Status.NOT_JAPANESE
                        return@OnInitListener
                    }
                    status = Status.FAILED
                })
        }

    private val hasJapanese: Boolean
        get() = textToSpeech?.isLanguageAvailable(Locale.JAPAN) ?: TextToSpeech.LANG_MISSING_DATA >= TextToSpeech.LANG_AVAILABLE

    fun isSpeaking(): Boolean {
        return textToSpeech?.isSpeaking ?: false
    }

    fun speech(
        message: String,
        utteranceProgressListener: UtteranceProgressListener? = null,
        pitchHeight: Float = 1.0f,
        pitchRate: Float = 1.0f
    ): Error {
        if (TextUtils.isEmpty(message)) return MESSAGE_EMPTY

        if (message.hasJapanese() && !hasJapanese) {
            return Error.NOT_JAPANESE
        }

        if (status != Status.SUCCESS) return UNKNOWN

        stop()

        textToSpeech?.let { tts ->
            utteranceProgressListener?.let {
                tts.setOnUtteranceProgressListener(it)
            }
            // set config
            tts.setPitch(pitchHeight)
            tts.setSpeechRate(pitchRate)

            // speech
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, "messageID")
            return NONE
        } ?: return NOT_CONTEXT
    }

    fun stop() {
        if (!isSpeaking()) return

        textToSpeech?.stop()
    }

    fun shutDown() {
        stop()

        textToSpeech?.shutdown()

        status = Status.NONE
    }

    fun disableSpeech() {
        status = Status.NO_SPEECH
    }

    fun canSpeech(): Boolean {
        return status == Status.NO_SPEECH
    }

    enum class Status {
        /** default */
        NONE,
        SUCCESS,
        FAILED,
        NOT_JAPANESE,
        NO_SPEECH
    }

    enum class Error {
        NONE,
        NOT_JAPANESE,
        MESSAGE_EMPTY,
        NOT_CONTEXT,
        UNKNOWN
    }
}