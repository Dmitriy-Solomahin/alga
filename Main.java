import java.util.Random;

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 20; i++)
            list.addSorted(new Random().nextInt(20));

        list.print();
        list.revert();
        list.print();
    }
}
