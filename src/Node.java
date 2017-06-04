/**
 * Created by eduardohenrique on 04/06/17.
 */
public class Node {
    private Node parent;

    private Node leftChild;

    private Node rightChild;

    private Integer value;

    private Color color;

    public Node() {}

    public Node(Integer value) {
        this(RBTree.NIL, RBTree.NIL, RBTree.NIL, value, Color.RED);
    }

    public Node(Node parent, Node leftChild, Node rightChild, Integer value, Color color) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
        this.color = color;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

enum Color {
    RED,BLACK
}