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

/**
 * Write a method to replace all strings with %20.
 * You may assume that the string has sufficient space at the end for additional characters
 * and that you are given the true length of the string
 * */
fun urlify(input: String, trueLength: Int): String {

    val inputArr = input.toCharArray()
    var spaceCt = 0

    for (i in 0..trueLength - 1)
        if (inputArr[i] == ' ') spaceCt++

    val newLength = trueLength + spaceCt * 2
    var w = newLength - 1

    var r = trueLength - 1
    while (w >= 0) {
        if (inputArr[r] != ' ') {
            inputArr[w--] = inputArr[r]
        } else {
            inputArr[w--] = '0'
            inputArr[w--] = '2'
            inputArr[w--] = '%'
        }
        r--
    }

    return inputArr.joinToString("")
}
/**
 * Given a string, write a function to check if it is a permutation of a palindrome.
 * APPARENTLY WE ARE ONLY COUNTING ALPHABET CHARACTERS
 * */
fun palindromePermutation(input: String): Boolean {
    val inputArr = input.toCharArray()
    // number of usages of each a-z char
    val usageCt = Array(26, { 0 })

    // length of string after removing non a-z chars and converting to lowercase
    var newLength = 0

    for (i in 0..inputArr.size - 1) {
        if (inputArr[i] in 'A'..'Z') {
            val lowerCase = inputArr[i] + 32
            usageCt[lowerCase - 'a']++
            newLength++
        } else if (inputArr[i] in 'a'..'z') {
            usageCt[inputArr[i] - 'a']++
            newLength++
        }
    }
    // strings with an odd length can have one "extra" character
    var odd = newLength % 2 == 1
    for (u in usageCt) {
        if (u % 2 == 1 && !odd)
            return false
        else if (u % 2 == 1 && odd)
            odd = false // the one extra character has been used
    }

    return true
}


/**
 * There are three types of edits that can be done to a string - insert, delete, swap
 * given two strings, check if they are 1 (or 0) edits away
 * */
fun oneAway(a: String, b: String): Boolean {
    // if the strings are more than off by 1 in length, no single operation will make them equal
    if (a.length > b.length + 1 || a.length < b.length - 1) return false

    // set flag when a single differing character is read
    var foundDiff = false

    // strings have equal length, check if swapping a single character will make them equal
    if (a.length == b.length) {
        var i = 0
        while (i < a.length) {
            if (a[i] != b[i++])
                if (foundDiff)
                // second differing char found, return false
                    return false
                else
                // first differing char found, set flag
                    foundDiff = true
        }

        return true
    }
    // strings have length differing by 1, check if a single insertion will make the shorter string equal the longer string
    else {
        val short = if (a.length > b.length) b else a
        val long = if (a.length > b.length) a else b

        var s = 0
        var l = 0

        while (s < short.length) {
            if (short[s++] != long[l++])
                if (foundDiff)
                // found multiple differing characters
                    return false
                else {
                    // found a single differing character, don't increment s this round so that we can check if the next character in the long string matches the current character
                    foundDiff = true
                    s--
                }
        }
        return true
    }
}

val uniqueStr = "abcdefghijk"
val nonUniqueStr = "abcdefghijka"
println("$uniqueStr is isUnique? ${isUnique(uniqueStr)}")
println("$nonUniqueStr is isUnique? ${isUnique(nonUniqueStr)}")

println("${uniqueStr.reversed()} is permutation of $uniqueStr? ${checkPermutation(uniqueStr, uniqueStr.reversed())}")
println("${uniqueStr.reversed()+"nope"} is permutation of $uniqueStr? ${checkPermutation(uniqueStr, uniqueStr.reversed()+"nope")}")

val urlStr = "mr john smith    "
println("$urlStr urlified is: ${urlify(urlStr, 13)}")

val palindromePerm = "taco cat#@$%@#$%"
val notPalindromePerm = "taco cat nope"
println("$palindromePerm is a palindromePermutation? ${palindromePermutation(palindromePerm)}")
println("$notPalindromePerm is a palindromePermutation? ${palindromePermutation(notPalindromePerm)}")

val oneAwayTestStrings = arrayOf(
            "" to "", // empty
            "dog" to "dog", // exact match
            "adog" to "dog", // insert/delete
            "dog" to "dogo", // insert/delete
            "dog" to "doggo",  // too long
            "doggo11" to "doggo00", // same length, too many different chars
            "dogg11" to "doggo00", // different length, too many different chars
            "dog" to "cat")
oneAwayTestStrings.forEach { println("${it.first} is oneAway from ${it.second}? ${oneAway(it.first, it.second)}") }
