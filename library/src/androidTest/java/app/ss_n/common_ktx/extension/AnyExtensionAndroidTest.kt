package app.ss_n.common_ktx.extension

import org.junit.Assert.assertEquals
import org.junit.Test

class AnyExtensionAndroidTest {
    @Test
    fun toBundleValue() {
        // given
        val key = "key"
        val value = "test"

        // when
        val bundle = value.toBundleValue(key)

        // than
        assertEquals(value, bundle[key])
    }
}
