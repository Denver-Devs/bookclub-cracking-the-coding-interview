# Chapter 1: Arrays and Strings

* String questions and Array questions are often interchangeable

## Hash Tables

* Maps keys to values for efficient lookup

### Array-Backed Hash Table

* Inserting a key:
  1. Compute the key's hash code 
      * Results in an `int` or `long`
  2. Map the hash code to an index of the array
      * eg, `hash(key) % array_length`
  3. Insert the k:v pair into the linked list at that index
      * Not a 1:1 mapping, the linked list handles collisions
* Retrieving a key is the same process
* Worst-case runtime is O(n), but is usually O(1)

### Balanced Binary Search Tree

* O(log N) lookup time
* Might use less space
* Can iterate through the keys in order

## ArrayList and Resizable Arrays

* An array-like data structure that offers dynamic resizing
* Offers O(1) access
* Doubles in size when the array is full
  * This process takes O(n) time because it has to touch all n elements to copy to the new array
  * Can be ignored because an insert is almost always O(1) and rarely O(n)

Example ArrayList use case:

```java
ArrayList<String> merge(String[] words, String[] moreWords) {
  ArrayList sentence = new ArrayList<String>();
  for (String word : words) sentence.add(word);
  for (String word : moreWords) sentence.add(word);
  return sentence;
}
```

## StringBuilder

* Instances of StringBuilder are not safe for use by multiple threads. If such synchronization is required then it is recommended that StringBuffer be used.
* String concatenation can be slow
  * A new copy of the string is created for each concatenation, because Strings are immutable
  * Assuming each string in the array is x chars long, each iteration n will take O(x + 2x + 3x + ... + nx) time, or O(xn^2) time

```java
String joinWords(String[] words) {
  String sentence = ""; // Strings are immutable in Java
  for (String word : words) {
    // A new String object of size sentence.length() + word.length() is created
    // and each character is touched copying from the old Strings to the new one 
    sentence = sentence + word;
  }
  return sentence;
}
```

* StringBuilder solves this problem by keeping a resizable array of Strings, copying them back to a string only when needed

```java
String joinWords(String[] words) {
  StringBuilder sentence = new StringBuilder();
  for (String word : words) {
    sentence.append(word); // Just adds it to the array
  }
  return sentence.toString(); // Does the concatenation only once
}
```
