package cs2720.p3;

// Generic Binary Search Tree implementation
public class BinarySearchTree<T extends Comparable<T>> {

        private NodeType<T> root;

        public BinarySearchTree() {
                this.root = null;
        }
        // Insert a new node; throws exception if duplicate
        public void insert(T key) {
                if (retrieve(key)) {
                        throw new IllegalArgumentException("The item already exists in the tree.");
                }
                root = insertRecursive(root, key);
        }
        // Recursive helper for insert
        private NodeType<T> insertRecursive(NodeType<T> node, T key) {
                if (node == null) {
                        return new NodeType<>(key);
                }

                int comparison = key.compareTo(node.getInfo());
                if (comparison < 0) {
                        node.setLeft(insertRecursive(node.getLeft(), key));
                } else if (comparison > 0) {
                        node.setRight(insertRecursive(node.getRight(), key));
                }

                return node;
        }
        // Delete a node; throws exception if not found
        public void delete(T key) {
                if (!retrieve(key)) {
                        throw new IllegalArgumentException("The number is not present in the tree");
                }
                root = deleteRecursive(root, key);
        }
        // Recursive helper for delete
        private NodeType<T> deleteRecursive(NodeType<T> node, T key) {
                if (node == null) {
                        return null;
                }

                int comparison = key.compareTo(node.getInfo());

                if (comparison < 0) {
                        node.setLeft(deleteRecursive(node.getLeft(), key));
                } else if (comparison > 0) {
                        node.setRight(deleteRecursive(node.getRight(), key));
                } else {
                        // Case 1: Leaf node
                        if (node.getLeft() == null && node.getRight() == null) {
                                return null;
                        }
                        // Case 2: Only right child
                        if (node.getLeft() == null) {
                                return node.getRight();
                        }
                        // Case 3: Only left child
                        if (node.getRight() == null) {
                                return node.getLeft();
                        }
                        // Case 4: Two children - use predecessor
                        NodeType<T> predecessor = findMax(node.getLeft());
                        node.setInfo(predecessor.getInfo());
                        node.setLeft(deleteRecursive(node.getLeft(), predecessor.getInfo()));
                }

                return node;
        }
        // Find maximum value in subtree (rightmost node)
        private NodeType<T> findMax(NodeType<T> node) {
                while (node.getRight() != null) {
                        node = node.getRight();
                }
                return node;
        }
        // Check if item exists in tree
        public boolean retrieve(T item) {
                return retrieveRecursive(root, item);
        }

        private boolean retrieveRecursive(NodeType<T> node, T item) {
                if (node == null) {
                        return false;
                }

                int comparison = item.compareTo(node.getInfo());

                if (comparison == 0) {
                        return true;
                } else if (comparison < 0) {
                        return retrieveRecursive(node.getLeft(), item);
                } else {
                        return retrieveRecursive(node.getRight(), item);
                }
        }
        // Print tree in sorted order (in-order traversal)
        public void inOrder() {
                System.out.print("In-order:");
                inOrderRecursive(root);
                System.out.println();
        }

        private void inOrderRecursive(NodeType<T> node) {
                if (node != null) {
                        inOrderRecursive(node.getLeft());
                        System.out.print(" " + node.getInfo());
                        inOrderRecursive(node.getRight());
                }
        }
        // Check if tree is proper (every node has 0 or 2 children)
        public boolean isProper() {
                return isProperRecursive(root);
        }

        private boolean isProperRecursive(NodeType<T> node) {
                if (node == null) {
                        return true;
                }
                // Check if node has exactly one child
                if ((node.getLeft() == null && node.getRight() != null)
                        || (node.getLeft() != null && node.getRight() == null)) {
                        return false;
                }

                return isProperRecursive(node.getLeft()) && isProperRecursive(node.getRight());
        }
        // Check if tree is complete
        public boolean isComplete() {
                int nodeCount = countNodes(root);
                return isCompleteRecursive(root, 0, nodeCount);
        }

        private int countNodes(NodeType<T> node) {
                if (node == null) {
                        return 0;
                }
                return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
        }
        // Use index-based checking for completeness
        private boolean isCompleteRecursive(NodeType<T> node, int index, int nodeCount) {
                if (node == null) {
                        return true;
                }

                if (index >= nodeCount) {
                        return false;
                }

                return isCompleteRecursive(node.getLeft(), 2 * index + 1, nodeCount)
                                && isCompleteRecursive(node.getRight(), 2 * index + 2, nodeCount);
        }
        // Find and print nodes with exactly one child
        public void getSingleParent() {
                System.out.print("Single Parents:");
                getSingleParentRecursive(root);
                System.out.println();
        }

        private void getSingleParentRecursive(NodeType<T> node) {
                if (node == null) {
                        return;
                }

                getSingleParentRecursive(node.getLeft());

                if ((node.getLeft() == null && node.getRight() != null)
                        || (node.getLeft() != null && node.getRight() == null)) {
                        System.out.print(" " + node.getInfo());
                }

                getSingleParentRecursive(node.getRight());
        }
        // Count and print number of leaf nodes
        public void getNumLeafNodes() {
                int count = countLeafNodes(root);
                System.out.println("The number of leaf nodes are " + count);
        }

        private int countLeafNodes(NodeType<T> node) {
                if (node == null) {
                        return 0;
                }

                if (node.getLeft() == null && node.getRight() == null) {
                        return 1;
                }

                return countLeafNodes(node.getLeft()) + countLeafNodes(node.getRight());
        }
        // Find and print cousins
        public void getCousins(T key) {
                System.out.print(key + " cousins:");

                int level = findLevel(root, key, 0);

                if (level == -1 || level == 0) {
                        System.out.println();
                        return;
                }

                NodeType<T> parent = findParent(root, key);

                findCousins(root, 0, level, parent);

                System.out.println();
        }
        // Find level of a node in the tree
        private int findLevel(NodeType<T> node, T key, int currentLevel) {
                if (node == null) {
                        return -1;
                }

                if (node.getInfo().compareTo(key) == 0) {
                        return currentLevel;
                }

                int leftLevel = findLevel(node.getLeft(), key, currentLevel + 1);
                if (leftLevel != -1) {
                        return leftLevel;
                }

                return findLevel(node.getRight(), key, currentLevel + 1);
        }
        // Find parent of a node
        private NodeType<T> findParent(NodeType<T> node, T key) {
                if (node == null || node.getInfo().compareTo(key) == 0) {
                        return null;
                }

                if ((node.getLeft() != null && node.getLeft().getInfo().compareTo(key) == 0)
                        || (node.getRight() != null && node.getRight().getInfo().compareTo(key) == 0)) {
                        return node;
                }

                NodeType<T> leftResult = findParent(node.getLeft(), key);
                if (leftResult != null) {
                        return leftResult;
                }

                return findParent(node.getRight(), key);
        }

        private void findCousins(NodeType<T> node, int currentLevel, int targetLevel,
                                                        NodeType<T> excludeParent) {
                if (node == null) {
                        return;
                }

                if (currentLevel == targetLevel) {
                        System.out.print(" " + node.getInfo());
                        return;
                }

                if (node != excludeParent) {
                        findCousins(node.getLeft(), currentLevel + 1, targetLevel, excludeParent);
                        findCousins(node.getRight(), currentLevel + 1, targetLevel, excludeParent);
                }
        }

        public NodeType<T> getRoot() {
                return root;
        }
}                           
