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
