/**
 * Created by eduardohenrique on 04/06/17.
 */
public class RBTree {

    public static String labelNil = "Nil";
    private int ZERO = 0;
    public static RBElement NIL = new RBElement(null, null, null, labelNil, Color.preto);

    private RBElement root;

    public RBTree() {
        this.root = NIL;
    }

    public RBTree(RBElement root) {
        this.root = root;
    }

    public void rbInsert(String key) {
        RBElement temp = root;
        RBElement pre = NIL;
        RBElement node = new RBElement(key);
        while (temp != NIL) {
            pre = temp;
            if (key.compareTo(temp.getKey()) < ZERO) {
                temp = temp.getLeftChild();
            } else {
                temp = temp.getRightChild();
            }
        }
        node.setParent(pre);
        if (pre == NIL) {
            root = node;
        } else if (key.compareTo(pre.getKey()) < ZERO) {
            pre.setLeftChild(node);
        } else {
            pre.setRightChild(node);
        }
        rbInsertFixup(node);
    }

    private void rbInsertFixup(RBElement node) {
        while (node.getParent().getColor() == Color.vermelho) {
            if (node.getParent() == node.getParent().getParent().getLeftChild()) {
                // Parent is left child of the grandfather
                RBElement uncle = node.getParent().getParent().getRightChild();
                if (uncle.getColor() == Color.vermelho) {  // CASE 1
                    node.getParent().setColor(Color.preto);
                    uncle.setColor(Color.preto);
                    node.getParent().getParent().setColor(Color.vermelho);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRightChild()) { // CASE 2
                        node = node.getParent();
                        leftRotate(node);
                    } else {  // CASE 3
                        node.getParent().setColor(Color.preto);
                        node.getParent().getParent().setColor(Color.vermelho);
                        rightRotate(node.getParent().getParent());
                    }
                }

            } else {
                // Parent is right child of the grandfather
                RBElement uncle = node.getParent().getParent().getLeftChild();
                if (uncle.getColor() == Color.vermelho) {  // CASE 1
                    node.getParent().setColor(Color.preto);
                    uncle.setColor(Color.preto);
                    node.getParent().getParent().setColor(Color.vermelho);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeftChild()) { // CASE 2
                        node = node.getParent();
                        rightRotate(node);
                    } else {  // CASE 3
                        node.getParent().setColor(Color.preto);
                        node.getParent().getParent().setColor(Color.vermelho);
                        leftRotate(node.getParent().getParent());
                    }
                }

            }
        }
        root.setColor(Color.preto);
    }

    public RBElement rbDelete(String key) {
        RBElement node = rbSearch(key);
        if (node == null) {
            return null;
        }
        RBElement temp = NIL;
        RBElement child = NIL;
        if (node.getLeftChild() == NIL || node.getRightChild() == NIL) {
            temp = node;
        } else {
            temp = successor(node);
//            temp = predecessor(node);
        }
        if (temp.getLeftChild() != NIL) {
            child = temp.getLeftChild();
        } else {
            child = temp.getRightChild();
        }
        child.setParent(temp.getParent());
        if (temp.getParent() == NIL) {
            root = child;
        } else if (temp == temp.getParent().getLeftChild()) {
            temp.getParent().setLeftChild(child);
        } else {
            temp.getParent().setRightChild(child);
        }
        if (temp != node) {
            node.setKey(temp.getKey());
        }
        if (temp.getColor() == Color.preto) {
            rbDeleteFixup(child);
        }
        return temp;

    }

    private void rbDeleteFixup(RBElement node) {
        while (node.getParent() != NIL && node.getColor() == Color.preto) {
            if (node == node.getParent().getLeftChild()) {
                RBElement brother = node.getParent().getRightChild();
                if (brother.getColor() == Color.vermelho) {
                    // CASE 1
                    node.getParent().setColor(Color.vermelho);
                    brother.setColor(Color.preto);
                    leftRotate(node.getParent());
                    brother = node.getParent().getRightChild();
                }
                if (brother.getLeftChild().getColor() == Color.preto && brother.getRightChild().getColor() == Color.preto) {
                    // CASE 2
                    brother.setColor(Color.vermelho);
                    node = node.getParent();
                } else if (brother.getRightChild().getColor() == Color.preto) {
                    // CASE 3
                    brother.setColor(Color.vermelho);
                    brother.getLeftChild().setColor(Color.preto);
                    rightRotate(brother);
                } else {
                    // CASE 4
                    brother.setColor(node.getParent().getColor());
                    node.getParent().setColor(Color.preto);
                    brother.getRightChild().setColor(Color.preto);
                    leftRotate(node.getParent());
                    node = root;
                }
            } else {
                RBElement brother = node.getParent().getLeftChild();
                if (brother.getColor() == Color.vermelho) {
                    // CASE 1
                    node.getParent().setColor(Color.vermelho);
                    brother.setColor(Color.preto);
                    rightRotate(node.getParent());
                    brother = node.getParent().getLeftChild();
                }
                if (brother.getLeftChild().getColor() == Color.preto && brother.getRightChild().getColor() == Color.preto) {
                    // CASE 2
                    brother.setColor(Color.vermelho);
                    node = node.getParent();
                } else if (brother.getLeftChild().getColor() == Color.preto) {
                    // CASE 3
                    brother.setColor(Color.vermelho);
                    brother.getRightChild().setColor(Color.preto);
                    leftRotate(brother);
                } else {
                    // CASE 4
                    brother.setColor(node.getParent().getColor());
                    node.getParent().setColor(Color.preto);
                    brother.getLeftChild().setColor(Color.preto);
                    rightRotate(node.getParent());
                    node = root;
                }
            }
        }
        node.setColor(Color.preto);
    }

    private RBElement successor(RBElement node) {
        if (node.getRightChild() != NIL) {
            RBElement temp = node.getRightChild();
            while (temp.getLeftChild() != NIL) {
                temp = temp.getLeftChild();
            }
            return temp;
        } else if (node.getParent() != NIL) {
            RBElement temp = node.getParent();
            while (temp != NIL && node != temp.getLeftChild()) {
                node = temp;
                temp = temp.getParent();
            }
            return temp;
        }
        return NIL;
    }

    private RBElement predecessor(RBElement node) {
        if (node.getLeftChild() != NIL) {
            RBElement temp = node.getLeftChild();
            while (temp.getRightChild() != NIL) {
                temp = temp.getRightChild();
            }
            return temp;
        } else if (node.getParent() != NIL) {
            RBElement temp = node.getParent();
            while (temp != NIL && node == temp.getLeftChild()) {
                node = temp;
                temp = temp.getParent();
            }
            return temp;
        }
        return NIL;
    }


    private RBElement rbSearch(String key) {
        RBElement temp = root;
        while (temp != NIL) {
            if (key.compareTo(temp.getKey()) == ZERO) {
                return temp;
            } else if (key.compareTo(temp.getKey()) < ZERO) {
                temp = temp.getLeftChild();
            } else {
                temp = temp.getRightChild();
            }
        }
        return null;
    }

    private void leftRotate(RBElement node) {
        RBElement rightChild = node.getRightChild();
        node.setRightChild(rightChild.getLeftChild());
        if (rightChild.getLeftChild() != NIL) {
            rightChild.getLeftChild().setParent(node);
        }
        rightChild.setLeftChild(node);
        rightChild.setParent(node.getParent());
        if (node.getParent() == NIL) {
            root = rightChild;
        } else if (node == node.getParent().getLeftChild()) {
            node.getParent().setLeftChild(rightChild);
        } else {
            node.getParent().setRightChild(rightChild);
        }
        node.setParent(rightChild);

    }

    private void rightRotate(RBElement node) {
        RBElement leftChild = node.getLeftChild();
        node.setLeftChild(leftChild.getRightChild());
        if (leftChild.getRightChild() != NIL) {
            leftChild.getRightChild().setParent(node);
        }
        leftChild.setRightChild(node);
        leftChild.setParent(node.getParent());
        if (node.getParent() == NIL) {
            root = leftChild;
        } else if (node == node.getParent().getLeftChild()) {
            node.getParent().setLeftChild(leftChild);
        } else {
            node.getParent().setRightChild(leftChild);
        }
        node.setParent(leftChild);

    }

    public void rbPrint(RBElement node) {
        if(node != NIL){
            rbPrint(node.getLeftChild());
            System.out.println(node.getKey());
            rbPrint(node.getRightChild());
        }
    }

    public void rbCheck(RBElement node) {
        if (node != NIL) {
//            rbCheck(node.getLeftChild());
//            System.out.println("Node[" + node.getValue() + "]color is:" + node.getColor());
            System.out.println(
                    "("+(node.getParent().getKey().compareTo(labelNil) != ZERO ? node.getParent().getKey() : labelNil)
                            + ", "+node.getKey()
                            + ", " + node.getColor()
                            + ", " + (node.getLeftChild().getKey().compareTo(labelNil) != ZERO ? node.getLeftChild().getKey() : labelNil)
                            + ", " + (node.getRightChild().getKey().compareTo(labelNil) != ZERO ? node.getRightChild().getKey() : labelNil)
                            + ")");
            rbCheck(node.getLeftChild());
            rbCheck(node.getRightChild());
        }
    }


    public RBElement getRoot() {
        return root;
    }
}
