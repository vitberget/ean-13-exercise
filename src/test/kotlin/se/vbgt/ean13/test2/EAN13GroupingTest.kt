package se.vbgt.ean13.test2


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import se.vbgt.ean13.EAN13

class EAN13GroupingTest {
    @Test
    fun `grouping test`() {
        assertEquals("LLGLGGRRRRRR", EAN13("1111111111116").groups())
        assertEquals("LGLGLGRRRRRR", EAN13("7310865001115").groups())
        assertEquals("LGLLGGRRRRRR", EAN13("4 003994 155486").groups())
    }
}