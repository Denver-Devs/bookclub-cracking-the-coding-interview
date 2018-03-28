//
//  main.swift
//  TreesGraphs
//
//  Created by Katie Noland on 3/26/18.
//  Copyright © 2018 CityLights. All rights reserved.
//

import Foundation

let root = Node("beverages")
let hot = Node("hot")
root.addNode(hot)

let tea = Node("tea")
hot.addNode(tea)
tea.addNode(Node("black"))
tea.addNode(Node("green"))
tea.addNode(Node("chai"))

hot.addNode(Node("coffee"))
hot.addNode(Node("cocoa"))

let cold = Node("cold")
root.addNode( cold)
let soda = Node("soda")
cold.addNode(soda)
soda.addNode(Node("ginger ale"))
soda.addNode(Node("bitter lemon"))

cold.addNode(Node("milk"))

root.printWithDepth()
print(root)
print(root.search(value: "milk"))
print(root.search(value: "carrots"))