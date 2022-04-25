//[library](../../../index.md)/[ss_n.common_ktx.observer](../index.md)/[Event](index.md)

# Event

[androidJvm]\
open class [Event](index.md)&lt;out [T](index.md)&gt;(content: [T](index.md))

https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150

## Constructors

| | |
|---|---|
| [Event](-event.md) | [androidJvm]<br>fun &lt;out [T](index.md)&gt; [Event](-event.md)(content: [T](index.md)) |

## Functions

| Name | Summary |
|---|---|
| [getContentIfNotHandled](get-content-if-not-handled.md) | [androidJvm]<br>fun [getContentIfNotHandled](get-content-if-not-handled.md)(): [T](index.md)?<br>Returns the content and prevents its use again. |

## Properties

| Name | Summary |
|---|---|
| [hasBeenHandled](has-been-handled.md) | [androidJvm]<br>var [hasBeenHandled](has-been-handled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
