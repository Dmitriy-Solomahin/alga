package RadBlackTree;

public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();

        tree.add(5);
        tree.add(1);
        tree.add(9);
        tree.add(8);
        tree.add(4);
        tree.add(7);
        tree.add(6);
        tree.add(2);
        tree.add(3);
        tree.add(11);
        tree.add(10);
        tree.add(12);
        tree.add(15);
        tree.add(13);
        tree.add(14);

        // tree.remove(8);
        tree.print();
    }
}
