//
// Created by Katie Noland on 3/24/18.
// Copyright (c) 2018 CityLights. All rights reserved.
//

import Foundation

/**
* A QUEUE
*/
class Queue<T> {
    private var first: Node<T>? = nil
    private var last: Node<T>? = nil

    private class Node<T> {
        let value: T
        var next: Node<T>? = nil

        init(_ data: T) {
            value = data
        }
    }

    // at end
    func add(value: T) {
        let new = Node(value)
        guard first != nil && last != nil else {
            first = new
            last = new
            return
        }
        last?.next = new
        last = last?.next

    }

    // from start
    func remove() throws -> T {
        guard let value = first?.value else {
            throw QueueError.emptyQueue
        }
        first = first?.next
        if first == nil {
            last = nil
        }
        return value
    }

    func peek() throws -> T {
        guard let value = first?.value else {
            throw QueueError.emptyQueue
        }
        return value
    }

    func isEmpty() -> Bool {
        return first == nil
    }

    func printQueue() throws {
        guard var current = first else {
            throw QueueError.emptyQueue
        }
        print(current.value)
        while let next = current.next {
            print(next.value)
            current = next
        }
    }
}


enum QueueError: Error {
    case emptyQueue
}