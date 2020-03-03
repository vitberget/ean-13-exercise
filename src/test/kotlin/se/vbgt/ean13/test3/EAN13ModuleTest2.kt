package se.vbgt.ean13.test3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import se.vbgt.ean13.EAN13

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EAN13ModuleTest2 {

    lateinit var testModule1:String
    lateinit var testModule2:String
    lateinit var testModule3:String
    lateinit var testModule4:String

    @BeforeAll
    fun `test bar 1`() {
        testModule1 = EAN13("1234567890128").modules()
        testModule2 = EAN13("0730143311809").modules()
        testModule3 = EAN13("7310865001115").modules()
        testModule4 = EAN13("7310865001115").modules()
    }

    @Test
    fun `correct lenght`() {
        assertEquals(95,testModule1.length)
        assertEquals(95,testModule2.length)
        assertEquals(95,testModule3.length)
        assertEquals(95,testModule4.length)
    }

    @Test
    fun `start marker`() {
        assertTrue(testModule1.startsWith("| |"))
        assertTrue(testModule2.startsWith("| |"))
        assertTrue(testModule3.startsWith("| |"))
        assertTrue(testModule4.startsWith("| |"))
    }

    @Test
    fun `end marker`() {
        assertTrue(testModule1.endsWith("| |"))
        assertTrue(testModule2.endsWith("| |"))
        assertTrue(testModule3.endsWith("| |"))
        assertTrue(testModule4.endsWith("| |"))
    }

    @Test
    fun `middle marker`() {
        assertEquals(" | | ", testModule1.subSequence(45,50))
        assertEquals(" | | ", testModule2.subSequence(45,50))
        assertEquals(" | | ", testModule3.subSequence(45,50))
        assertEquals(" | | ", testModule4.subSequence(45,50))
    }
}