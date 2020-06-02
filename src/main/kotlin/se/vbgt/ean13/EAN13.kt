package se.vbgt.ean13

import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Font
import java.awt.Font.PLAIN
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_BYTE_BINARY
import java.io.File
import javax.imageio.ImageIO

class EAN13(number: String) {

    // I'm lazy, so I throw exceptions in alot in when():s.
    // If for real use, do better!

    // remove whitespace
    private val theNumber: String = number.replace("""\s+""".toRegex(), "")

    init {
        // validate length and check digit
        require(theNumber.length == 13) { "Bad length" }
        require(checkDigit(theNumber.take(12)) == theNumber.last()) { "Bad check digit" }
    }

    fun groups(): String =
    // The first digit determines the group of the next six digits
        // The last six digits always have group R
        when (theNumber.first()) {
            '0' -> "LLLLLLRRRRRR"
            '1' -> "LLGLGGRRRRRR"
            '2' -> "LLGGLGRRRRRR"
            '3' -> "LLGGGLRRRRRR"
            '4' -> "LGLLGGRRRRRR"
            '5' -> "LGGLLGRRRRRR"
            '6' -> "LGGGLLRRRRRR"
            '7' -> "LGLGLGRRRRRR"
            '8' -> "LGLGGLRRRRRR"
            '9' -> "LGGLGLRRRRRR"
            else -> throw IllegalArgumentException("Not a number")
        }

    fun modules(): String {
        val groups = groups()

        // The first digit determines the group of the next six digits
        val part1 = theNumber.drop(1).take(6)
            .mapIndexed { i: Int, c: Char -> mapModule(groups[i], c) }
            .joinToString("")

        // The last six digits always have group R
        val part2 = theNumber.drop(7)
            .map { mapModule('R', it) }
            .joinToString("")

        return "| |${part1} | | ${part2}| |"
    }

    companion object {
        fun mapModule(group: Char, digit: Char): String =
            when (group) {
                'L' -> mapModuleL(digit)
                'G' -> mapModuleG(digit)
                'R' -> mapModuleR(digit)
                else -> throw IllegalArgumentException("Not a group")

                // To lazy to manually replace 1 with | and 0 with space from the copy-pasted data from Wikipedia
            }.replace('1', '|').replace('0', ' ')

        private fun mapModuleR(digit: Char): String =
            when (digit) {
                '0' -> "1110010"
                '1' -> "1100110"
                '2' -> "1101100"
                '3' -> "1000010"
                '4' -> "1011100"
                '5' -> "1001110"
                '6' -> "1010000"
                '7' -> "1000100"
                '8' -> "1001000"
                '9' -> "1110100"
                else -> throw IllegalArgumentException("Not a digit")
            }

        private fun mapModuleG(digit: Char): String = mapModuleR(digit).reversed()

        private fun mapModuleL(digit: Char): String =
            mapModuleR(digit)
                .map { invertBinaryDigitChar(it) }
                .joinToString("")

        private fun invertBinaryDigitChar(c: Char): Char =
            when (c) {
                '1' -> '0'
                '0' -> '1'
                else -> throw IllegalArgumentException("Not a binary digit")
            }
    }


    private fun checkDigit(value: String): Char {
        val inverseMod10 = value.reversed()
            .mapIndexed { i, c -> checkDigitMultiplier(i, c) }
            .sum()
            .rem(10)

        // 10 - the remainder can become 10, so we do a remainder again to zero out 10
        val mod10 = (10 - inverseMod10).rem(10)

        return '0' + mod10
    }

    private fun checkDigitMultiplier(i: Int, c: Char): Int =
        (c - '0') * if (i.isEven()) 3 else 1

    fun saveImageTo(path: String) {
        val image = createImage()
        val outputFile = File(path)
        ImageIO.write(image, "png", outputFile)
    }

    private fun createImage(): BufferedImage {
        val width = 230
        val height = 120

        val image = BufferedImage(width, height, TYPE_BYTE_BINARY)
        val graphics2D = prepareGraphics2D(image, width, height)

        drawModules(graphics2D)
        drawNumbers(graphics2D)

        return image
    }

    private fun drawNumbers(graphics2D: Graphics2D) =
        theNumber.forEachIndexed { i: Int, c: Char -> drawNumber(i, c, graphics2D) }

    private fun drawNumber(index: Int, c: Char, graphics2D: Graphics2D) {
        val x = index * 14 +
                when (index) {
                    0 -> 9
                    in 1..6 -> 15
                    in 7..12 -> 23
                    else -> throw IllegalArgumentException("Outside range")
                }

        graphics2D.drawString(c.toString(), x, 104)
    }

    private fun drawModules(graphics2D: Graphics2D) {
        modules().forEachIndexed { i: Int, c: Char ->
            if (c == '|') drawModule(i, graphics2D)
        }
    }

    private fun drawModule(i: Int, graphics2D: Graphics2D) {
        val height = when (i) {
            in 0..2 -> 80   // start marker
            in 45..49 -> 80 // middle marker
            in 92..94 -> 80 // end marker

            else -> 70
        }
        val x = 20 + i * 2
        graphics2D.fillRect(x, 20, 2, height)
    }

    private fun prepareGraphics2D(image: BufferedImage, width: Int, height: Int): Graphics2D {
        val graphics2D = image.createGraphics()
        graphics2D.background = WHITE
        graphics2D.clearRect(0, 0, width, height)
        graphics2D.color = BLACK
        graphics2D.font = Font("TimesRoman", PLAIN, 16)
        return graphics2D
    }
}

private fun Int.isEven(): Boolean = this % 2 == 0

fun main() {
    EAN13("1234567890128").saveImageTo("123.png")
}
