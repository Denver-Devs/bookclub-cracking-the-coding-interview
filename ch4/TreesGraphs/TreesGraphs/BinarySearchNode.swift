//
// Created by Katie Noland on 3/26/18.
// Copyright (c) 2018 CityLights. All rights reserved.
//

import Foundation

/**
* Binary Search Tree where the left node is always less than the right node
*/
enum BinarySearch {
    class Node<T: Comparable> {
        var value: T
        var left: Node?
        var right: Node?

        init(_ value: T) {
            self.value = value
        }

        func insert(_ value: T) {
            if (value <= self.value) {
                if (self.left == nil) {
                    self.left = Node(value)
                } else {
                    self.left?.insert(value)
                }
            } else {
                if (self.right == nil) {
                    self.right = Node(value)
                } else {
                    self.right?.insert(value)
                }
            }
        }

        func preOrderTraverse(_ doForEach: (T) -> Void) {
            left?.preOrderTraverse(doForEach)
            doForEach(value)
            right?.preOrderTraverse(doForEach)
        }

        func postOrderTraverse(_ doForEach: (T) -> Void) {
            right?.postOrderTraverse(doForEach)
            doForEach(value)
            left?.postOrderTraverse(doForEach)
        }
    }
}

extension BinarySearch.Node: CustomStringConvertible {
    var description: String {
        return "\(left?.description ?? "")\(value), \(right?.description ?? "")"
    }
}
