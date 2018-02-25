class LinkedList<T> {
    var head: Node<T>? = null

    /**
     * Add a value to the end of the list
     * */
    fun add(value: T) {
        if (head == null) {
            head = Node(value)
            return
        }

        var current = head
        while (current?.next != null)
            current = current.next

        current?.next = Node(value)
    }

    /**
     * Delete the first occurrence of a value in a list
     * */
    fun delete(value: T) {
        if (head?.data == value) {
            head = head?.next
            return
        }

        var current = head
        while (current?.next != null) {
            if (current?.next?.data == value) {
                current?.next = current?.next?.next
                return
            }
            current = current?.next
        }
    }

    class Node<T>(val data: T) {
        var next: Node<T>? = null
    }
}

/**
 * A hash table implementation using linked list chaining to handle collision detection
 * */
class HashTable<K : Any, V> {
    val array = Array(256, { LinkedList<Pair<K, V>>() })

    operator fun get(key: K): V? {
        var current = array[key.indexForKey].head
        // check if the head node matches the key
        if (current?.data?.first == key) return current?.data?.second

        // iterate through the list searching for a node matching the key
        while (current?.next != null) {
            if (current?.next?.data?.first == key) return current?.next?.data?.second
            current = current?.next
        }
        // no key matches were found, return null
        return null
    }

    operator fun set(key: K, value: V) {
        val newNode = LinkedList.Node(key to value)
        val list = array[key.indexForKey]
        var current = list.head

        // create the head
        if (current == null) {
            list.head = newNode
            return
        }
        // overwrite the value of the head, re-link
        if (current?.data?.first == key) {
            val tail = list.head?.next
            list.head = newNode
            list.head?.next = tail
            return
        }

        // iterate through list, searching for a key match
        while (current?.next != null) {
            // if a node with a matching key is found, overwrite it and re-link the tail of the list
            if (current?.next?.data?.first == key) {
                val tail = current?.next?.next
                current?.next = newNode
                current?.next?.next = tail
                return
            }
            current = current?.next
        }

        // no nodes with matching keys were found, append our new node to the list
        list.add(key to value)
    }

    /**
     * Given a key, take its hashCode and map that to an index in the array
     * */
    private val K.indexForKey
        get() = this.hashCode().rem(array.size)
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
