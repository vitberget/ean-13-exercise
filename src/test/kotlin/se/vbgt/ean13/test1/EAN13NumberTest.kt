package se.vbgt.ean13.test1


import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import se.vbgt.ean13.EAN13
import java.lang.IllegalArgumentException


class EAN13NumberTest {

    @Test
    fun `not shorter than 13`() {
        assertThrows(IllegalArgumentException::class.java) { EAN13("1") }
        assertThrows(IllegalArgumentException::class.java) { EAN13("12345") }
        assertThrows(IllegalArgumentException::class.java) { EAN13("123456789012") }
        assertThrows(IllegalArgumentException::class.java) { EAN13("123456789012") }
    }

    @Test
    fun `not longer than 13`() {
        assertThrows(IllegalArgumentException::class.java) { EAN13("12345678901234") }
        assertThrows(IllegalArgumentException::class.java) { EAN13("1234567890123456") }
        assertThrows(IllegalArgumentException::class.java) { EAN13("1234567890123456789") }
    }

    @Test
    fun `incorrect check digit`() {
        assertThrows(IllegalArgumentException::class.java) { EAN13("1234567890123") }
        assertThrows(IllegalArgumentException::class.java) { EAN13("1111111111111") }
        assertThrows(IllegalArgumentException::class.java) { EAN13("1212121212121") }

    }

    @Test
    fun `ok ean13 codes`() {
        EAN13("7310865001115")
        EAN13("1234567890128")
        EAN13("1212121212128")
        EAN13("7310865001115")
        EAN13("0730143311809")
        EAN13("2109876543210")
    }

    @Test
    fun `it should be ok to have whitespace`() {
        EAN13("4 003994 155486")
    }

}