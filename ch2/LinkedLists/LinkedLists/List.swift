//
// Created by katien on 3/16/18.
// Copyright (c) 2018 katien. All rights reserved.
//

import Foundation

class List<T> {
    var head: Node<T>?

    init?(_ values: [T]) {
        guard let first = values.first else {
            return nil
        }

        head = Node(first)
        var current = head
        values.suffix(from: 1).forEach { item in
            current?.next = Node(item)
            current = current?.next
        }
    }


    var last: Node<T>? {
        var current = head
        while let next = current?.next {
            current = next
        }
        return current
    }
    var penultimate: Node<T>? {
        var current = head
        while let next = current?.next {
            // check if there's another item after the next item
            if (next.next != nil) {
                current = next
            } else {
                break
            }
        }
        return current
    }

    subscript(index: Int) ->  Node<T>? {
        var current = head
        for _ in 0..<index {
            guard let next = current?.next else {
                return nil
            }
            current = next
        }
        return current
    }

    var length: Int {
        var current = head
        var ct = 0
        while let next = current?.next {
            current = next
            ct += 1
        }
        return ct
    }

    func printList() {
        var current = head
        print(head!.value)
        while let next = current?.next {
            current = next
            print(next.value)
        }
    }

    func reverse() {
        var newTail: Node<T>? = nil
        var oldTail: Node<T>? = head?.next
        var current: Node<T>? = head

        while (current != nil) {
            current?.next = newTail
            newTail = current
            current = oldTail
            oldTail = current?.next
        }

        self.head = newTail
    }
}

class Node<T> {
    init(_ v: T) {
        value = v
    }

    var value: T
    var next: Node<T>?
}
