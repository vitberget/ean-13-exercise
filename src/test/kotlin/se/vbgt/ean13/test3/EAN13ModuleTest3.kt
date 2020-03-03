package se.vbgt.ean13.test3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import se.vbgt.ean13.EAN13

class EAN13ModuleTest3 {

    @Test
    fun `module 1`() {
        assertEquals(
            "| |  ||  |   || |  | |||   |  | ||| ||    | | | | |  ||| | |||  |    | || ||  ||  || |||  | | |",
            EAN13("2109876543210").modules()
        )
    }

    @Test
    fun `module 2`() {
        assertEquals(
            "| | ||| || |||| |   || |  ||  | |   || |||| | | | |    | ||  || ||  || |  |   |||  | ||| |  | |",
            EAN13("0730143311809").modules()
        )
    }

    @Test
    fun `module 3`() {
        assertEquals(
            "| | |||| | ||  ||   || |   |  | | |||| |||  | | | |||  | |||  | ||  || ||  || ||  || |  ||| | |",
            EAN13("7310865001115").modules()
        )
    }

    @Test
    fun `module 4`() {
        assertEquals(
            "| |  |  ||  ||  |  || ||  ||  |  || || ||  || | | || ||  ||  || || ||  ||  || || ||  |  |   | |",
            EAN13("1212121212128").modules()
        )
    }

    @Test
    fun `module 5`() {
        assertEquals(
            "| |  |  || |||| |  ||| | ||   |    | |  |   | | | |  |   ||| |  |||  | ||  || || ||  |  |   | |".replace('1','|').replace('0',' '),
            EAN13("1234567890128").modules()
        )
    }
}