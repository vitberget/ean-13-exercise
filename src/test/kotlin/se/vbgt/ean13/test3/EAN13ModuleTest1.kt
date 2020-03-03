package se.vbgt.ean13.test3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import se.vbgt.ean13.EAN13

class EAN13ModuleTest1 {

    @Test
    fun `group R` () {
        assertEquals("||  || ", EAN13.mapModule('R', '1'))
        assertEquals("| |||  ", EAN13.mapModule('R', '4'))
        assertEquals("|   |  ", EAN13.mapModule('R', '7'))
    }

    @Test
    fun `group G` () {
        assertEquals(" |  |||", EAN13.mapModule('G', '0'))
        assertEquals("  || ||", EAN13.mapModule('G', '2'))
        assertEquals("  | |||", EAN13.mapModule('G', '9'))
    }

    @Test
    fun `group L` () {
        assertEquals(" |||| |", EAN13.mapModule('L', '3'))
        assertEquals(" | ||||", EAN13.mapModule('L', '6'))
        assertEquals(" || |||", EAN13.mapModule('L', '8'))
    }
}