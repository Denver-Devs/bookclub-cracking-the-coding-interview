//
//  main.swift
//  StacksQueues
//
//  Created by Katie Noland on 3/24/18.
//  Copyright © 2018 CityLights. All rights reserved.
//

import Foundation

let stacky = Stack<Int>()

for item in 0...10 {
    stacky.push(item)
}
stacky.printStack()
while stacky.isEmpty() == false {
    try print(stacky.pop())
}
