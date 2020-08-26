package app.ss_n.common_ktx.extension

import org.junit.Assert.assertEquals
import org.junit.Test

import java.util.Calendar
import java.util.Locale

class DateExtensionTest {

    @Test
    fun toFormat() {
        // given
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.clear()
        calendar.set(2020, 0, 1)

        // when
        val result = calendar.time.toFormat()

        // than
        assertEquals("2020/01/01 00:00:00", result)
    }

    @Test
    fun toYear() {
        // given
        val year = 1990
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.clear()
        calendar.set(1990, 0, 1)

        // when
        val result = calendar.time.toYear()

        // than
        assertEquals(year, result)
    }

    @Test
    fun toMonth() {
        // given
        val month = 10
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.clear()
        calendar.set(2020, month, 1)

        // when
        val result = calendar.time.toMonth()

        // than
        assertEquals(month, result)
    }

    @Test
    fun toDay() {
        // given
        val day = 26
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.clear()
        calendar.set(2020, 1, day)

        // when
        val result = calendar.time.toDay()

        // than
        assertEquals(day, result)
    }

    @Test
    fun toHour() {
        // given
        val hour = 3
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.clear()
        calendar.set(2020, 0, 1, hour, 30)

        // when
        val result = calendar.time.toHour()

        // than
        assertEquals(hour, result)
    }

    @Test
    fun toMinute() {
        // given
        val minute = 15
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.clear()
        calendar.set(2020, 0, 1, 12, minute)

        // when
        val result = calendar.time.toMinute()

        // than
        assertEquals(minute, result)
    }

    @Test
    fun toSecond() {
        // given
        val second = 30
        val calendar = Calendar.getInstance(Locale.JAPAN)
        calendar.clear()
        calendar.set(2020, 0, 1, 12, 15, second)

        // when
        val result = calendar.time.toSecond()

        // than
        assertEquals(second, result)
    }
}
