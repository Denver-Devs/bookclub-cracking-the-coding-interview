//
//  main.swift
//  TreesGraphs
//
//  Created by Katie Noland on 3/26/18.
//  Copyright © 2018 CityLights. All rights reserved.
//

import Foundation

let nums = BinarySearch.Node(10)
nums.insert(1)
nums.insert(4)
nums.insert(16)
nums.insert(5)
nums.insert(17)
nums.insert(2)
nums.insert(3)
nums.insert(12)
nums.insert(13)
nums.insert(14)
nums.insert(17)
nums.insert(2)
nums.insert(3)
nums.insert(12)
nums.insert(13)
nums.insert(17)
// behold, the binary search tree will always print the Generic.Nodes in orderr
print(nums)
nums.preOrderTraverse { print($0, terminator: ", ")}
print()
nums.postOrderTraverse { print($0, terminator: ", ")}

let root = Generic.Node("beverages")
let hot = Generic.Node("hot")
root.addNode(hot)

let tea = Generic.Node("tea")
hot.addNode(tea)
tea.addNode(Generic.Node("black"))
tea.addNode(Generic.Node("green"))
tea.addNode(Generic.Node("chai"))

hot.addNode(Generic.Node("coffee"))
hot.addNode(Generic.Node("cocoa"))

let cold = Generic.Node("cold")
root.addNode( cold)
let soda = Generic.Node("soda")
cold.addNode(soda)
soda.addNode(Generic.Node("ginger ale"))
soda.addNode(Generic.Node("bitter lemon"))

cold.addNode(Generic.Node("milk"))

root.printWithDepth()
print(root)
print(root.search(value: "milk"))
print(root.search(value: "carrots"))