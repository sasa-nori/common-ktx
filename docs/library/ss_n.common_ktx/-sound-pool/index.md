//[library](../../../index.md)/[ss_n.common_ktx](../index.md)/[SoundPool](index.md)

# SoundPool

[androidJvm]\
object [SoundPool](index.md)

SoundPool [android.media.SoundPool](https://developer.android.com/reference/kotlin/android/media/SoundPool.html) Wrapper Class

## Functions

| Name | Summary |
|---|---|
| [init](init.md) | [androidJvm]<br>fun [init](init.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), files: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;, usage: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = AudioAttributes.USAGE_GAME, contentType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = AudioAttributes.CONTENT_TYPE_SPEECH, listener: ([MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>example is [ss_n.common_ktx.extension.loadSoundPool](../../ss_n.common_ktx.extension/load-sound-pool.md) |
| [play](play.md) | [androidJvm]<br>fun [play](play.md)(playId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), left: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 1.0f, right: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 1.0f, priority: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, loop: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, rate: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 1.0f)<br>If you specify the loaded path in init(), the sound will be played. |
| [release](release.md) | [androidJvm]<br>fun [release](release.md)()<br>memory release |
