# String Search
## Brute force search for a pattern with length `p` in a string of length `t` takes _O(p(t-p)_
  - Take the length of the string, subtract the length of the substring `t-p`
  - Search through the characters in the string from index `0` to `t-p` in a loop
  - for each character, check if it and the following `p` characters match the pattern
```kotlin
fun containsBruteForce(target: String, pattern: String): Boolean {
    // loop through the target string until 1 char past the target length - the pattern length
    for (i in 0..target.length - pattern.length) {
        // loop through the pattern and check if the corresponding char in the target matches
        // if the whole pattern is looped through without breaking, it was all found
        for (p in 0..pattern.length - 1) {
            if (pattern[p] != target[i + p]) break
            if (p == pattern.length - 1) return true
        }
    }
    return false
}
```
## Rabin–Karp
1. Iterate through the characters in the target string and calculate the hash of the substring with the length of the pattern
  - If two strings are equal they have the same hash
2. With an efficient hash function, getting the hash for each index and checking to see if it matches the hash of the substring only takes `O(t)` time
3. Since the hash of two substrings can match without the substrings being equal, each hashed sequence needs to be checked for true equality

```kotlin
fun isSubstringHashNaive(s: String, b: String): Boolean {
    if (s.length == 0 || b.length == 0 || s.length > b.length) return false

    val sHash = s.hash()

    for (i in 0..b.length - s.length) {
        // substring in b from i to i+s.length
        var sub = b.substring(i, i + s.length)
        if (sub.hash() == sHash) {
            // hashes match, check for true equality
            if (sub == s) return true
        }
    }

    return false
}
```


# Hash Table
- Hash tables have an internal array of "buckets" used to store sets of key value pairs
- A hashing function is used to match a key to a bucket at some array index
- When the hashing function creates a collision, the implementation's collision resolution strategy will determine where the new value should go
- Load factor is the number of entries / length of the table's internal array
  * the goal is to keep load factor low and buckets as small as possible
  * higher load factor leads to decreases in performance

### Separate Chaining
- Buckets in the array are independent of each other and use some list data structure to hold a set of key value pairs
  * linked list chaining is most common
  * binary trees can also be used if uneven distribution is expected
- Look up a value for a key by running the hash function on the key and then searching the bucket's list for a pair containing the matching key
  * lookup cost for linked list chaining is proportional to the size of the bucket since the cost of locating the bucket is fixed and from there the list just needs to be traversed (O(n) runtime)
  * binary search tree chaining has a worst case runtime O(log n)

### Open Addressing
- Pairs are stored directly in the hash table's array instead of in other data structures referenced by the array
- A hashing algorithm is used to select an index for the key, if there is a collision a probing algorithm is used to find the next empty slot in the array
  * the probe will be repeated until an empty slot is found
- Look up a value for a key by using the hashing function to get an index and checking to see if the pair contains the matching key. If it does not, use the probing function to determine the next index to check.
  * When the probe returns an index with no key value pair, it was unsuccessful and no value exists for the key
- Deleting an item is tricky because you can't just set the slot in the array to `null` or the probing algorithm will halt each time it reaches that index. Instead, set some flag/marker value at that slot
- Linear Probing tends to result in all values being clustered together in groups in the hash table's array, use a quadratic probing algorithm to reduce clustering

# Dynamic Arrays
- Arrays in Java are fixed length, size is determined when the array is created
  * In many languages the resizable version of an array is called a list
- Resizable arrays are implemented with a backing internal static array and a length counter to determine whether the value being accessed is within the bounds initialized by the user
  * When the length counter reaches the size of the static array, the static array is rebuilt to be larger and all values are copied to the new array
- Java `ArrayList` - An array like data structure which will resize automatically
  * Resizes by allocating a new array twice as long internally and then copying all values from the original array over (O(n) runtime)
- Amortized insertion time for `ArrayList`s is just O(1) because most insertions will be standard array insertions. Certain insertions will take O(n) time because they require rebuilding the array, but these will happen exponentially less often as the array grows  
- Amortized analysis evaluates an algorithm's time complexity without looking exclusively at the worst case scenario, so even though worst case scenario for the `ArrayList` insertion is O(n), the amortized cost is lower

### StringBuilder
- Strings are immutable in java so every time you add something to a string, you're really copying everything in the original string and the string being appended and creating a new string
- `StringBuilder` uses a dynamic array to allow inexpensive concatenation to a string, then it converts the array of string chunks to a full string when needed
