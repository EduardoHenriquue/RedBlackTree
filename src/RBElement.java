/**
 * Created by eduardohenrique on 04/06/17.
 */
public class RBElement {
    private RBElement parent;

    private RBElement leftChild;

    private RBElement rightChild;

    private String key;

    private Color color;

    public RBElement() {}

    public RBElement(String key) {
        this(RBTree.NIL, RBTree.NIL, RBTree.NIL, key, Color.vermelho);
    }

    public RBElement(RBElement parent, RBElement leftChild, RBElement rightChild, String key, Color color) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.key = key;
        this.color = color;
    }

    public RBElement getParent() {
        return this.parent;
    }

    public void setParent(RBElement parent) {
        this.parent = parent;
    }

    public RBElement getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(RBElement leftChild) {
        this.leftChild = leftChild;
    }

    public RBElement getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(RBElement rightChild) {
        this.rightChild = rightChild;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

enum Color {
    vermelho,preto
}