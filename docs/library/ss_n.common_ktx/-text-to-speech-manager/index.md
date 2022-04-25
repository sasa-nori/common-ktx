//[library](../../../index.md)/[ss_n.common_ktx](../index.md)/[TextToSpeechManager](index.md)

# TextToSpeechManager

[androidJvm]\
object [TextToSpeechManager](index.md)

## Types

| Name | Summary |
|---|---|
| [Error](-error/index.md) | [androidJvm]<br>enum [Error](-error/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[TextToSpeechManager.Error](-error/index.md)&gt; |
| [Status](-status/index.md) | [androidJvm]<br>enum [Status](-status/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[TextToSpeechManager.Status](-status/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [canSpeech](can-speech.md) | [androidJvm]<br>fun [canSpeech](can-speech.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [disableSpeech](disable-speech.md) | [androidJvm]<br>fun [disableSpeech](disable-speech.md)() |
| [isSpeaking](is-speaking.md) | [androidJvm]<br>@[JvmStatic](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/index.html)<br>fun [isSpeaking](is-speaking.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [shutDown](shut-down.md) | [androidJvm]<br>fun [shutDown](shut-down.md)() |
| [speech](speech.md) | [androidJvm]<br>fun [speech](speech.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), utteranceProgressListener: [UtteranceProgressListener](https://developer.android.com/reference/kotlin/android/speech/tts/UtteranceProgressListener.html)? = null, pitchHeight: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 1.0f, pitchRate: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 1.0f): [TextToSpeechManager.Error](-error/index.md) |
| [stop](stop.md) | [androidJvm]<br>fun [stop](stop.md)() |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | [androidJvm]<br>var [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)? = null |
