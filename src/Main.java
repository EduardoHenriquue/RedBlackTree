/**
 * Created by eduardohenrique on 04/06/17.
 */
public class Main {
    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        Integer[] values = new Integer[]{4,8,5,12,24,6,7,28,35,14,2,13,19,32,17};
        for (Integer value : values) {
            rbTree.insert(value);
            System.out.println("\nNode inserted: " +value);
            System.out.println("\n=====   Tree   =====");
            rbTree.print();
        }


        System.out.println("\n=====   DELETE   =====");
        System.out.println("\nDeleting 6");
        rbTree.delete(6);
        System.out.println("\n=====   Tree   =====");
        rbTree.print();
        System.out.println("\nDeleting 4");
        rbTree.delete(4);
        System.out.println("\n=====   Tree   =====");
        rbTree.print();
        System.out.println("\nDeleting 12");
        rbTree.delete(12);
        System.out.println("\n=====   Tree   =====");
        rbTree.print();
        System.out.println("\nDeleting 24");
        rbTree.delete(24);

        System.out.println("=====   Final Tree   =====");
        rbTree.print();
    }
}
