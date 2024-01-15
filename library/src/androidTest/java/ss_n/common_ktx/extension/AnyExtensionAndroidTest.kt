package ss_n.common_ktx.extension

import com.google.common.truth.Truth.assertThat
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
        assertThat(bundle.getString(key)).isEqualTo(value)
    }
}
