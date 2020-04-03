package net.newstyleservice.common_ktx.extension

import org.junit.Assert.assertEquals
import org.junit.Test

internal class Int_ExtensionTest {
    @Test
    fun decimalText() {
        // given
        val answer = "10,000"
        val target: Int = 10000

        // when
        val result = target.decimalText()

        assertEquals(result, answer)
    }
}
