package ss_n.common_ktx.extension

import org.junit.Assert.assertEquals
import org.junit.Test

class AnyExtensionTest {

    @Test
    fun toMutableMapValue() {
        // given
        val key = "key"
        val value = "test"

        // when
        val map = value.toMutableMapValue(key)

        // than
        assertEquals(value, map[key])
    }

    @Test
    fun toMapValue() {
        // given
        val key = "key"
        val value = "test"

        // when
        val map = value.toMapValue(key)

        // than
        assertEquals(value, map[key])
    }
}
