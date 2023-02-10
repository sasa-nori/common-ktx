//[library](../../index.md)/[ss_n.common_ktx.extension](index.md)/[save](save.md)

# save

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.WRITE_EXTERNAL_STORAGE&quot;, &quot;android.permission.READ_EXTERNAL_STORAGE&quot;])

fun [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html).[save](save.md)(filePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), format: [Bitmap.CompressFormat](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.CompressFormat.html) = Bitmap.CompressFormat.JPEG, quality: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 100, isErrorLog: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Bitmap save

#### Return

true:File saved

## Parameters

androidJvm

| | |
|---|---|
| filePath | File Path |
| format | default: JPEG |
| quality | default: 100 |
| isErrorLog | default: false |
