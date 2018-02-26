class ResizableArray<K> {
    var array: Array<Any?> = arrayOfNulls(256)
    var size = 0

    fun add(value: K) {
        array[size] = value
        size++

        if (size == array.size)
            array = Array(array.size * 2, { if (it < size) array[it] else null })
    }

    operator fun get(index: Int): K {
        if (index < size)
            @Suppress("UNCHECKED_CAST")
            return array[index] as K
        else
            throw IndexOutOfBoundsException("The array is only $size long you can't access index $index")
    }

    operator fun set(index: Int, value: K) {
        if (index < size)
            array[index] = value
        else
            throw IndexOutOfBoundsException("The array is only $size long you can't access index $index")
    }
}


class StringMaker {
    val chunks = ResizableArray<String>()

    fun append(str: String) = chunks.add(str)

    override fun toString(): String {
        var str = ""
        for (i in 0..chunks.size - 1)
            str += chunks[i]
        return str
    }
}

val question = ResizableArray<String>()
question.add("What")
question.add("is")
question.add("for")
question.add("dinner")

question[3] = "dinner?"

(0..question.size - 1).forEach { println("question[$it]: ${question[it]}") }


val answerStringBuilder = StringMaker()
answerStringBuilder.append("Probably ")
answerStringBuilder.append("some ")
answerStringBuilder.append("sushi.")
println(answerStringBuilder.toString())
