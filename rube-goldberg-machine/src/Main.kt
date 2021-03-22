var start: Int = 5 // 1. Global field

fun main() {
    val startString = intToString(start)
    val magicNumber: Int = stringToInt(startString) % MaleNames.values().size
    val name: String = when (magicNumber) { // 2. = when(val)
        0 -> MaleNames.PAVLO.getName()
        1 -> MaleNames.NEKIT.getName()
        else -> MaleNames.SASHA.getName()
    }
    val map: Map<Int, Any> = mapOf(magicNumber to name) // 4. mapOf(K to V). 5. Any

    val man: Man = if (isAllStrings(map[magicNumber])) Man(map[magicNumber] as String) else Man()
    // 6. Cast as. 7. Inline if. 8. Creating object without new

    for (i in map.size.."${man.firstName}${man.lastName}".length step man.write().length) { // Expression language
        // 9. for in step
        start += i
    }

    println(start)
}

fun intToString(number: Int): String = "$number" // 10. fun = body. 11. "$val"

fun stringToInt(str: String): Int = Integer.valueOf(str)

fun isAllStrings(vararg values: Any?): Boolean { // 12. Vararg. 13. Type?, can be null
    values.forEach { el -> if (el !is String) return false } // 14. forEach lambda. 15. !is
    return true
}
