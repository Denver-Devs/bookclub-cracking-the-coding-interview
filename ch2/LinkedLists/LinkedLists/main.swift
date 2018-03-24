import Foundation

/**
* 2.1 Remove dups
* write code to remove duplicates from an unsorted linked list
* how would you do this without a temporary buffer?
*/
extension List where T: Equatable & Hashable {

    // no buffer, o(n^2) time
    func removeDuplicates() {
        var current = head
        while current != nil {
            var runner = current
            while runner != nil {
                // runner?.next is a duplicate, cut it out
                if runner?.next?.value == current?.value {
                    runner?.next = runner?.next?.next
                } else {
                    runner = runner?.next
                }
            }
            current = current?.next
        }
    }


    // buffer, o(n) time
    func removeDuplicatesBuffered() {
        var current = head
        var values = Set<T>()

        while let next = current?.next {
            values.insert((current?.value)!)

            if values.contains(next.value) {
                current?.next = next.next
            } else {
                current = next
            }

        }
    }

}

// "d", "e", "b", "b", "c", "d", "e","c", "d", "e", "f"
if let list1a = List([8, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 6, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 2, 2, 5]) {
    print("List with duplicates: \(list1a.toString)")
    list1a.removeDuplicatesBuffered()
    print("List without duplicates: \(list1a.toString)")
}

/**
* 2.2 Return kth to last
* implement an algorithm to find the kth to last element of a singly linked list
*/
extension List {
    func kFromEnd(k: Int) -> Node<T>? {
        var length = 0
        var current = head

        while let next = current?.next {
            length += 1
            current = next
        }
        current = head
        for _ in 0..<length - k {
            current = current?.next
        }
        return current
    }
}


/**
* 2.3 Delete middle node
* Implement an algorithm to delete a node from a linked list given access to only that node
*/
extension List {
    func deleteNode(node: Node<T>) {
        if (node.next == nil || head === node) {
            return
        }

        // copy the value of the next node into the current node, then delete the next node
        if let next = node.next {
            node.value = next.value
            node.next = next.next
        }
    }
}

/**
* 2.4
* Write code to partition a linked list around a value x
*/
extension List where T: Comparable {
    func partition(x: T) {
        var smallPart: Node<T>?, smallTail: Node<T>?, bigPart: Node<T>? = nil

        var current = head
        while let next = current?.next {
            if ((current?.value)! > x) {
                current?.next = bigPart
                bigPart = current
            } else {
                if smallTail == nil {
                    smallTail = current
                }
                current?.next = smallPart
                smallPart = current
            }
            current = next
        }
        head = smallPart
        smallTail?.next = bigPart
    }
}

/**
* 2.5
* You have two numbers represented by a linked list, where each node
* contains a single digit. The digits are stored in reverse order,
* such that the 1’s digit is at the head of the list. Write a function
* that adds the two numbers and returns the sum as a linked list.
*
* 1->2->9 + 2->4->1 = 921+142 = 1063
*/
func sum(l1: List<Int>, l2: List<Int>) -> List<Int> {
    var n1 = l1.head
    var n2 = l2.head

    var n1Digit = l1.head?.value
    var n2Digit = l2.head?.value

    var sum = 0
    var tens = 1

    while n1Digit != nil || n2Digit != nil {
        sum += ((n1Digit ?? 0) + (n2Digit ?? 0)) * tens
        tens *= 10

        n1Digit = n1?.next?.value
        n2Digit = n2?.next?.value

        n1 = n1?.next
        n2 = n2?.next
    }
    return List<Int>(String(sum).map {
        Int(String($0))!
    })!
}

/**
* 2.5
* ok now do it again but backwards
* 1->2->9 + 2->4->1 = 129+241 = 370
*/
func sumReversed(l1: List<Int>, l2: List<Int>) -> List<Int> {
    var tens = 1

    var n1 = l1.head
    var n2 = l2.head

    // iterate through the longest list to set the tens multiplier and pad the shorter list with 0 Nodes
    while true {
        tens *= 10
        if n1?.next == nil && n2?.next == nil {
            break
        }
        // pad the shorter list with 0s
        if n1?.next == nil {
            let newHead = Node(0)
            newHead.next = l1.head
            l1.head = newHead
        }
        if n2?.next == nil {
            let newHead = Node(0)
            newHead.next = l2.head
            l2.head = newHead
        }
        n1 = n1?.next
        n2 = n2?.next
    }

    n1 = l1.head
    n2 = l2.head

    var n1Digit = l1.head?.value
    var n2Digit = l2.head?.value
    var sum = 0

    while n1Digit != nil || n2Digit != nil {
        tens /= 10
        sum += ((n1Digit ?? 0) + (n2Digit ?? 0)) * tens

        n1Digit = n1?.next?.value
        n2Digit = n2?.next?.value

        n1 = n1?.next
        n2 = n2?.next
    }
    return List<Int>(String(sum).map {
        Int(String($0))!
    })!
}

/**
* 2.6
* Implement a function to check if the list is a palindrome
*/
extension List where T: Equatable {
    func reversed() -> List<T> {
        var current = head
        var newTail: Node<T>? = nil
        var oldTail = head?.next

        while (current != nil) {
            current?.next = newTail
            newTail = current
            current = oldTail
            oldTail = current?.next
        }
        return List(head: newTail)
    }

    func isPalindrome() -> Bool {
        // obtain length of list
        var length = 1
        var current = head
        while let next = current?.next {
            length += 1
            current = next
        }

        // obtain the node before the middle node in the list
        var b4mid = head
        for _ in 0..<(length / 2) - 1 {
            b4mid = b4mid?.next
        }
        // delete the middle node if the list has an odd length
        if (length % 2 == 1) {
            b4mid?.next = b4mid?.next?.next
        }

        // check that the reversed second half of the list matches the first half of the list
        var reversedTail = List(head: b4mid?.next).reversed()
        guard var n = self.head, var r = reversedTail.head else {
            return false
        }

        if (n.value != r.value) {
            return false
        }
        while let rNext = r.next, let nNext = n.next {
            if (nNext.value != rNext.value) {
                return false
            }
            r = rNext
            n = nNext
        }
        return true
    }
}

/**
* 2.7
* Given two singly linked lists, check if they intersect and return the intersecting node
*/
func intersection<T>(l1: List<T>, l2: List<T>) -> Node<T>? {
    guard var l1current = l1.head, var l2current = l2.head else {
        return nil
    }
    while let l1next = l1current.next {
        while let l2next = l2current.next {
            if (l2current === l1current) {
                return l2current
            }
            l2current = l2next
        }

        l1current = l1next
        l2current = l2.head!
    }
    return nil
}

/**
* 2.8
* Given a corrupt linked list find the start of the loop
*/
extension List {
    func loop() -> Node<T>? {
        guard var fast = head, var slow = head else {
            return nil
        }
        while let slownext = slow.next {
            while let fastnext = fast.next {
                if (fast === slow) {
                    return fast.next
                }
                fast = fastnext
            }

            slow = slownext
            fast = self.head!
        }
        return nil
    }
}



