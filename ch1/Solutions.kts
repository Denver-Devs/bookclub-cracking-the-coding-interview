/**
 * Implement an algorithm to determine if a string has all unique characters
 * What if you cannot use additional data structures?
 * */
fun isUnique(input: String): Boolean {
    val inputArr = input.toCharArray()
    val charsUsed = Array(256, { false })
    for (c in inputArr) {
        if (charsUsed[c.toInt()])
            return false
        else
            charsUsed[c.toInt()] = true
    }

    return true
}

/**
 * Given two strings, write a method to decide if one is a permutation of the other
 */
fun checkPermutation(a: String, b: String): Boolean {
    if (a.length != b.length) return false

    val aChars = a.toCharArray()
    val bChars = b.toCharArray()

    val charCountA = Array(256, { 0 })
    val charCountB = Array(256, { 0 })
    for (i in 0..aChars.size - 1) {
        charCountA[aChars[i].toInt()]++
        charCountB[bChars[i].toInt()]++
    }

    for (i in 0..charCountA.size - 1)
        if (charCountA[i] != charCountB[i]) return false

    return true
}

val uniqueStr = "abcdefghijk"
val nonUniqueStr = "abcdefghijka"
println("$uniqueStr is isUnique? ${isUnique(uniqueStr)}")
println("$nonUniqueStr is isUnique? ${isUnique(nonUniqueStr)}")

println("${uniqueStr.reversed()} is permutation of $uniqueStr? ${checkPermutation(uniqueStr, uniqueStr.reversed())}")
println("${uniqueStr.reversed()+"nope"} is permutation of $uniqueStr? ${checkPermutation(uniqueStr, uniqueStr.reversed()+"nope")}")
