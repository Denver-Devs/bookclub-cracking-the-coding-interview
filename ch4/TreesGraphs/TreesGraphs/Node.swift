//
// Created by Katie Noland on 3/26/18.
// Copyright (c) 2018 CityLights. All rights reserved.
//

import Foundation
/**
* Generic tree node, can have any number of children in any order
*/
class Node<T> {
    var value: T
    var children: [Node<T>] = []
    weak var parent: Node? = nil

    init(_ value: T) {
        self.value = value
    }

    func addNode(_ child: Node<T>) {
        children.append(child)
        child.parent = self
    }

    func printWithDepth(_ depth: Int = 0) {
        print(depth, value)
        children.forEach {
            $0.printWithDepth(depth + 1)
        }
    }
}

extension Node: CustomStringConvertible {
    var description: String {
        let childDescriptions = children.isEmpty ? "" : "{ \(children.map {$0.description}.joined(separator: ", "))}"
        return "\(value) \(childDescriptions)"
    }
}

extension Node where T: Equatable {
    var description: String {
        let childDescriptions = children.isEmpty ? "" : "{ \(children.map {$0.description}.joined(separator: ", "))}"
        return "\(value) \(childDescriptions)"
    }

    func search(value: T) -> Node<T>? {
        if self.value == value {
            return self
        }

        var found: Node<T>? = nil
        children.forEach {
            found = $0.search(value: value)
        }
        return found
    }
}