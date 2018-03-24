//
// Created by Katie Noland on 3/24/18.
// Copyright (c) 2018 CityLights. All rights reserved.
//

import Foundation

/**
* A STACK
*/
class Stack<T> {
    var top: Node<T>? = nil

    class Node<T> {
        let value: T
        var next: Node<T>? = nil

        init(data: T) {
            value = data
        }
    }

    func pop() throws -> T {
        guard let value = top?.value else {
            throw StackError.emptyStack
        }
        top = top?.next
        return value
    }

    func push(_ value: T) {
        let next = Node(data: value)
        next.next = top
        top = next
    }

    func isEmpty() -> Bool {
        return top == nil
    }

    func peek() -> T? {
        return top?.value
    }

    func printStack() {
        var current = top
        print("Top:\(current?.value ?? nil)")
        while let next = current?.next {
            print(next.value)
            current = next
        }
    }
}


enum StackError: Error {
    case emptyStack
}