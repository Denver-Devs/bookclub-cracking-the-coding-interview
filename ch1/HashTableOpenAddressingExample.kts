class HashTable<in K : Any, V> {
    var itemCount: Int = 0

    private val MAX_LOAD_FACTOR = 0.2

    private var array = Array<Pair<K, V>?>(256, { null })

    operator fun set(key: K, value: V) {
        var index = key.indexForKey
        // iterate through the array using the probing algorithm
        while (array[index] != null) {
            // if a pair with a matching key is found, overwrite it with a new pair containing the new data
            if (array[index]?.first == key) {
                array[index] = key to value
                return
            }
            index = probeNext(index)
        }
        // maximum load factor has been exceeded, rebuild the array
        // call set again so that the key will be rehashed against the new array size
        if (++itemCount / array.size >= MAX_LOAD_FACTOR) {
            rebuild()
            set(key, value)
        } else {
            // the key doesn't already exist, add it at the first null slot
            array[index] = key to value
        }
    }

    operator fun get(key: K): V? {
        var index = key.indexForKey
        while (array[index] != null) {
            if (array[index]?.first == key) return array[index]?.second
            index = probeNext(index)
        }
        return null
    }

    // hashing function to find an index for a key
    private val K.indexForKey
        get() = this.hashCode() % array.size


    // Probing algorithm (linear)
    private fun probeNext(current: Int): Int = if (current < array.size - 1) current + 1 else 0

    // reallocate an array twice the size of the original
    private fun rebuild() {
        val oldArray = array
        array = Array(array.size * 2, { null })
        // rehash values from the original array
        for (i in oldArray)
            if (i != null) array[i.first.indexForKey] = i
    }
}


val humans = HashTable<String, Int?>()
humans["katie"] = 21
humans["james"] = 17
humans["matthew"] = 12

humans["james"] = null
humans["james"] = 18

println("katie ${humans["katie"]}")
println("james ${humans["james"]}")
println("matthew ${humans["matthew"]}")
