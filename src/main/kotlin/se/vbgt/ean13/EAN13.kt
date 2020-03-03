package se.vbgt.ean13

class EAN13(number: String) {

    fun groups(): String = TODO()
    fun modules(): String = TODO()

    companion object {
        fun mapModule(group: Char, digit: Char): String = TODO()
    }

    fun saveImageTo(path: String): Unit = TODO("Bonus")
}
