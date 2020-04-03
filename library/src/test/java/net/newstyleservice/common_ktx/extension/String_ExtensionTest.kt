package net.newstyleservice.common_ktx.extension

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class String_ExtensionTest {

    @Test
    fun isMailAddress() {
        // given
        val target = "test.test_test-test@test.com"

        // when
        val result = target.isMailAddress {
            assert(false)
        }

        // than
        assertTrue(result)
    }

    @Test
    fun isMailAddress_2() {
        // given
        val target = "test.test_test-test@test.com"
        val errorMessage = "Oh! is not Mail Address"

        // when
        val result = target.isMailAddress(errorMessage = errorMessage) {
            assertEquals(it, errorMessage)
            assert(false)
        }

        // than
        assertTrue(result)
    }

    @Test
    fun isMailAddress_error() {
        // given
        val target = "test.test_test-testtest.com"

        // when
        val result = target.isMailAddress {
            assert(true)
        }

        // than
        assertFalse(result)
    }

    @Test
    fun isPhoneNumber_1() {
        // given
        val target = "0123456789"

        // when
        val result = target.isPhoneNumber {
            assert(false)
        }

        // than
        assertTrue(result)
    }

    @Test
    fun isPhoneNumber_2() {
        // given
        val target = "01234567890"

        // when
        val result = target.isPhoneNumber {
            assert(false)
        }

        // than
        assertTrue(result)
    }

    @Test
    fun isPhoneNumber_error() {
        // given
        val target = "test-000-aaa"

        // when
        val result = target.isPhoneNumber {
            assert(true)
        }

        // than
        assertFalse(result)
    }

    @Test
    fun isNumber() {
        // given
        val target = "1234567890"

        // when
        val result = target.isNumber {
            assert(false)
        }

        // than
        assertTrue(result)
    }

    @Test
    fun isNumber_error() {
        // given
        val target = "test000aaa"

        // when
        val result = target.isNumber {
            assert(true)
        }

        // than
        assertFalse(result)
    }

    @Test
    fun isInt() {
        // given
        val target = "12345678901"

        // when
        val result = target.isInt {
            assert(false)
        }

        // than
        assertTrue(result)
    }

    @Test
    fun isInt_error_1() {
        // given
        val target = "123456789012"

        // when
        val result = target.isInt {
            assert(true)
        }

        // than
        assertFalse(result)
    }

    @Test
    fun isInt_error_3() {
        // given
        val target = "abcde"

        // when
        val result = target.isInt {
            assert(true)
        }

        // than
        assertFalse(result)
    }
}