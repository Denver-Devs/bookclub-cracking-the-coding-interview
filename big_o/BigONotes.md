# Time Complexity
- The actual runtime of an algorithm will vary depending on implementation details
  * speed of the computer used
  * compiler optimizations
  * language
- Asymptotic runtime of an algorithm is the way its runtime increases relative to the input size
  * if you graphed the algorithm's runtime against its input value, the curve of the graph would be its asymptotic runtime
  * Big O stands for order because it measures the Order (growth rate) of a function
- The order/growth rate of a function is based only on the dominant term. Constant multipliers and non dominant values are dropped
  * `O(N + log N)` => `O(N)`
  * `O(.5N)` => `O(N)`
- Big O only describes the _rate_ of increase of a function's runtime
  * a function with a lower asymptotic runtime `O(1)` could still execute more quickly than one with a higher asymptotic runtime  `O(N)` for certain values
  * asymptotic runtime refers to runtime for increasing inputs, it only guarantees behavior after inputs reach a sufficient size

# Space Complexity
- Space Complexity is also described with big O, it refers to the amount of memory required by an algorithm based on its input size
- Stack space in recursive functions must also be considered in space cost analysis
- The unoptimized recursive function here adds a new stack frame for each `sumRecursive` call, so it has a time complexity of `O(N)`
  * an iterative approach or a function using tail call optimization would only require a single frame to exist at any given time, resulting in a constant space complexity `O(1)`
```python
# O(N)
def sumRecursive(i):
  if i >= 0:
    return i + sumRecursive(i-1)
  else:
    return 0
# O(1)
def sumIterative(i):
    result = 0
    for n in range(i+1):
        result += n
    return result
```

# Big O, Big Ω, and Big Θ
- There are multiple types of asymptotic notation that can be used to describe a function's runtime growth
- Big Omega `Ω` - loose lower bound on runtime growth
  * the runtime of a function with `Ω(N^2)` will increase relative to its input size at a rate of _at least_ `N^2`
  * the boundary is loose, so a function with `Ω(N^2)` also has `Ω(N)`, `Ω(1)`, and any other smaller Omega
- Big O `O` - mathematically, big O actually refers to a loose upper bound on runtime growth
  * in the context of computer science, we treat big O as a tight upper bound
  * the runtime of a function with `O(N^2)` will increase relative to its input size at a rate of _no more than_ `N^2`
  * the boundary is loose, so a function with `O(N^2)` technically also has `Ω(N^3)`, `Ω(2^N)`, and any other larger O.
  * Don't say this to people because in computer science we pretend the bound is tight
- Big Theta `Θ` - tight runtime bound
  * big theta is the intersection of the lower and upper runtime bounds
  * it can vary by only a constant factor
  * a function with `Θ(N^2)` has a runtime which will grow no slower than some multiple of `N^2` and no faster than some (other) multiple of `N^2`
  * For an algorithm to have a runtime with a tight bound `Θ(f(N))` there must exist a function `f(N)` which is both `O(f(N))` and `Ω(f(N))`
- We use a combination of Big O and Big Theta in computer science. The big O of an algorithm is the _tight upper bound_
  * same as the mathematical Big Theta
  * the smallest Big O function possible

# Best, Worst, and Expected Case
- An algorithm's runtime can vary for inputs of the same size, consider quicksort with an array of size `N`
  * Best case: if the array is already sorted, each element is traversed only once and the runtime cost will be linearly proportional to the size of the array (`O(N)`)
  * Worst case: if the array is sorted in reverse order, each iteration of the quicksort only reduces the array size by 1. The runtime would be `N + N-1 + N-2 + N-3 + ... + 2 + 1 + N-N` which works out to `O(N^2)`
  * Expected case: on average, the quicksort will split a random array evenly, reducing the number of elements to be traversed by half with each iteration. This results in a runtime of `O(N log N)`
- Best case is useless, any algorithm will have a constant best case if you special case the inputs
```python
def specialCase(a):
  # O(1)
  if (a.__len__() == 10):
     print "constant runtime"
     return

  # O(N^2)
  for i in a:
    for j in a:
      print "{0}, {1}".format(i, j)
```
- The asymptotic worst case runtime for an algorithm can be different from the asymptotic runtime of the average case
  * Worst case and average case are often the same, but in cases like quicksort both asymptotic runtimes are useful
- Best, expected, and worst case are completely unrelated to theta, O, and omega

# Multipart algorithms
- If an algorithm has two steps, find the asymptotic runtime of each step, then combine them either by adding or multiplying
- if the second step is done _after_ the first step runs to completion, add their runtimes
- if the second step is done _each time_ the first step runs, multiply their runtimes

# Multivariable runtimes
- An algorithm's (simplified) asymptotic runtime can include multiple variables if it takes more than 1 input which cannot be described relative to another input
- The simplified asymptotic time cost of painting a wall with a given width and height would be `O(W*H)` assuming width and height were unrelated
  * if we knew width and height were related in some way, we could express the runtime in a simplified single variable form
  ex: Width = Height * Height
  `O(W*H)` =>  `O(H*H*H)` => `O(H^3)`
- Another example is sorting the characters of all the strings in an array. The runtime of a string sort is `O(N log N)` for a string of length N. The array's length will be unrelated though, so the algorithm's runtime is `O(A*N log N)`

# Amortized Runtime Analysis
- Worst case runtime cost of a set of operations averaged over the number of runs
  * different from average runtime because it doesn't consider probability, it assumes the worst case possible for a series of runs and guarantees an average performace for all runs in that case
  * Instead of looking at the highest runtime possible, the amortized analysis adds the runtimes for inputs 0 through N, then divides the result by N
- Amortized analysis is used for when the worst case runtime for an algorithm is too pessimistic
  * an occasional run will be slow, but most runs will be much faster
  * often used with data structures where the algorithm being analysed alters the state of the structure, preventing the more expensive run from happening again soon
  * balancing a binary tree is complex with a cost of `O(n)` but only happens every `n` insertions(`1/n` insertions will result in the balance). `O(n) * 1/n = O(1)`, so the amortized complexity of insertion into a binary tree is `O(1)`
