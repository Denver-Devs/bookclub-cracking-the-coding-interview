
/**
 * Implement an algorithm to determine if a string has all unique characters. What if you
 * can not use additional data structures?
 */
fun unique(input: String): Boolean {
    // integer values of characters that have been found
    val foundChars = Array(256, { false })
    for (i in input)
        if (foundChars[i.toInt()]) return false
        else foundChars[i.toInt()] = true
    return true
}

/**
 * Write code to reverse a C-Style String. (C-String means that “abcd” is represented as
 * five characters, including the null character.)
 * */
fun backwards(input: String): String {
    var strArr = input.toCharArray()
    var tmp: Char
    var end = input.length - 2
    var start = 0

    while (start < end) {
        tmp = strArr[start]
        strArr[start++] = strArr[end]
        strArr[end--] = tmp
    }
    return strArr.contentToString()
}
