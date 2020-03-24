[library](../../index.md) / [net.newstyleservice.common_ktx](../index.md) / [SharedPrefDelegated](./index.md)

# SharedPrefDelegated

`abstract class SharedPrefDelegated`

### Constructors

| [&lt;init&gt;](-init-.md) | `SharedPrefDelegated(context: Context)` |

### Functions

| [nullablePref](nullable-pref.md) | `fun <T> nullablePref(): `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`SharedPrefDelegated`](./index.md)`, `[`T`](nullable-pref.md#T)`?>` |
| [pref](pref.md) | `fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> pref(default: `[`T`](pref.md#T)`): `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`SharedPrefDelegated`](./index.md)`, `[`T`](pref.md#T)`>` |
| [put](put.md) | `fun <T> put(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`T`](put.md#T)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extension Functions

| [toJsonString](../../net.newstyleservice.common_ktx.extension/kotlin.-any/to-json-string.md) | `fun `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`.toJsonString(isLog: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |

