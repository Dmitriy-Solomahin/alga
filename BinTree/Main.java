package BinTree;

public class Main {
    public static void main(String[] args) {
        BinTree<Integer> tree = new BinTree<Integer>();

        tree.add(5);
        tree.add(1);
        tree.add(9);
        tree.add(8);
        tree.add(4);
        tree.add(7);
        tree.add(6);
        tree.add(2);
        tree.add(3);

        tree.remove(4);
        tree.print();
    }
}
