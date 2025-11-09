# Interactive-BST
## Description

A comprehensive Binary Search Tree (BST) implementation in Java featuring generic type support (int, double, String), advanced tree operations including cousin detection and structural validation, and an interactive CLI. Demonstrates mastery of recursive algorithms, tree traversal patterns, and Big-O complexity analysis through recurrence relations solved via the Master Theorem.

---

# 🌳 Binary Search Tree - Advanced Tree Operations

A robust, generic Binary Search Tree implementation supporting multiple data types with advanced structural analysis operations. Features interactive CLI, relationship queries, and comprehensive algorithmic complexity documentation.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8.6-red.svg)](https://maven.apache.org/)
[![Data Structures](https://img.shields.io/badge/Data%20Structures-BST-blue.svg)]()

## 🎯 Project Overview

Binary Search Tree implementation featuring:
- **Generic Type System**: Supports `int`, `double`, and `String` through Java Generics
- **Structural Analysis**: Validates tree properties (proper, complete)
- **Relationship Queries**: Finds cousins, single parents, and leaf node counts
- **Interactive CLI**: User-friendly command interface for tree manipulation

## ✨ Key Features

### Core Operations
- **Insert/Delete/Search**: Standard BST operations with all edge cases
- **In-Order Traversal**: Display tree contents in sorted order
- **Get Cousins**: Find nodes at the same depth with different parents
- **Single Parent Detection**: Identify nodes with exactly one child
- **Leaf Node Count**: Count terminal nodes efficiently
- **Tree Validation**: Check if tree is proper (0 or 2 children) or complete

### Technical Highlights
- Pure linked structure using custom `NodeType` (no arrays/collections)
- Java Generics with `<T extends Comparable<T>>`
- Recursive and iterative implementations
- Exception handling for invalid operations
- Big-O complexity analysis with Master Theorem

## 🛠️ Technical Stack

| Component | Technology |
|-----------|------------|
| **Language** | Java 17 |
| **Build Tool** | Maven 3.8.6 |
| **Testing** | JUnit 5 |
| **Type System** | Java Generics |

## 📁 Project Structure
```
project3/
├── src/main/java/cs2720/p3/
│   ├── BinarySearchTreeDriver.java    # CLI interface
│   ├── BinarySearchTree.java          # Core BST logic
│   └── NodeType.java                  # Generic tree node
├── src/test/java/cs2720/p3/
│   └── BinarySearchTreeTest.java      # JUnit tests
├── test-data/
│   ├── int-input.txt
│   ├── double-input.txt
│   └── string-input.txt
└── pom.xml
```

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8.6+

### Compilation & Execution
```bash
# Compile
mvn clean compile

# Run with integer data
mvn exec:java -Dexec.mainClass="cs2720.p3.BinarySearchTreeDriver" \
  -Dexec.args="test-data/int-input.txt"

# Run tests
mvn test
```

### Manual Compilation
```bash
mkdir -p bin
javac -d bin src/main/java/cs2720/p3/*.java
java -cp bin cs2720.p3.BinarySearchTreeDriver test-data/int-input.txt
```

## 🎮 Interactive Commands
```
(i) Insert Item       (d) Delete Item       (p) Print Tree
(r) Retrieve Item     (l) Count Leaf Nodes  (s) Find Single Parents
(c) Find Cousins      (o) Is Proper         (m) Is Complete
(q) Quit
```

## 💻 Usage Example
```bash
Enter list type (i - int, d - double, s - string): i

Enter a command: p
In-order: 4 10 12 15 18 22 24 25 35 50

Enter a command: c
Enter a number: 12
12 cousins: 18 24

Enter a command: l
The number of leaf nodes are 5
```

**Example Tree:**
```
       25
      /  \
    15    50
   /  \    \
  10  22   35
 / \  / \
4  12 18 24
```

## 📊 Algorithm Complexity Analysis

### Operation Complexities

| Operation | Average | Worst Case | Space |
|-----------|---------|------------|-------|
| Insert/Delete/Search | O(log n) | O(n) | O(1) |
| Traversal | O(n) | O(n) | O(h) |
| Get Cousins | O(n) | O(n) | O(h) |
| Leaf Count | O(n) | O(n) | O(h) |
| Single Parents | O(n) | O(n) | O(h) |

### Recurrence Relations & Master Theorem

**Get Single Parents:**
```
T(n) = 2T(n/2) + O(1)
Using Master Theorem: a=2, b=2, f(n)=O(1)
log_b(a) = 1, Case 1 applies
Result: T(n) = Θ(n)
```

**Get Cousins:**
```
Requires 3 tree traversals (find depth, find parent, print cousins)
Each: T(n) = 2T(n/2) + O(1) = O(n)
Total: 3 * O(n) = O(n)
```

**Leaf Count:**
```
T(n) = 2T(n/2) + O(1)
Master Theorem Case 1
Result: T(n) = Θ(n)
```

## 🧪 Key Algorithms

### Delete Node (Two Children)
```
1. Find in-order predecessor (rightmost in left subtree)
   OR successor (leftmost in right subtree)
2. Replace node's value with predecessor/successor
3. Recursively delete the predecessor/successor
```

### Tree Properties

**Proper Tree**: Every node has exactly 0 or 2 children
```
     10          ✓ Proper
    /  \
   5    15
  / \   / \
 3   7 12  20
```

**Complete Tree**: All levels full except possibly last (filled left-to-right)
```
     10          ✓ Complete
    /  \
   5    15
  / \   /
 3   7 12
```

## 🎓 Learning Outcomes

This project demonstrates:
- **Data Structures**: BST implementation, tree traversals, linked structures
- **Algorithms**: Recursion, tree operations, deletion strategies
- **Analysis**: Big-O notation, recurrence relations, Master Theorem
- **Engineering**: Maven builds, JUnit testing, generic programming

## 📝 Future Enhancements

- [ ] Self-balancing trees (AVL/Red-Black)
- [ ] Range queries and bulk operations
- [ ] Tree visualization with JavaFX
- [ ] Iterative traversals (Morris)
- [ ] Thread-safe concurrent BST

## 👨‍💻 Developer

**Eshan** - Computer Science & AI Student  
University of Georgia | Class of 2027
---

**For Recruiters**: Demonstrates strong foundations in algorithm design, mathematical complexity analysis, generic programming, and problem-solving. The Master Theorem proofs and advanced tree operations showcase theoretical CS knowledge applicable to optimizing production systems.
