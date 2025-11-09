package cs2720.p3;

// Generic node class for Binary Search Tree
public class NodeType<T extends Comparable<T>> {


    private T info;
    private NodeType<T> left;
    private NodeType<T> right;

    // Constructor to create a new node with the given value
    public NodeType(T info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodeType<T> getLeft() {
        return left;
    }

    public void setLeft(NodeType<T> left) {
        this.left = left;
    }

    public NodeType<T> getRight() {
        return right;
    }

    public void setRight(NodeType<T> right) {
        this.right = right;
    }
}
