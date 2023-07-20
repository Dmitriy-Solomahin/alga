public class LinkedList {

    private Node root;
    private int size;

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }
        Node currentNode = root;
        while (currentNode != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node(value);
        size++;
    }

    public void addSorted(int value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }
        Node addedNode = new Node(value);
        if (root.value > value) {
            addedNode.next = root;
            root = addedNode;
            size++;
            return;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value > value) {
                addedNode.next = currentNode.next;
                currentNode.next = addedNode;
                size++;
                return;
            }
            currentNode = currentNode.next;
        }
        currentNode.next = addedNode;
        size++;
    }

    public boolean remove(int value) {
        if (root == null) {
            return false;
        }
        if (root.value == value) {
            root = root.next;
            size--;
            return true;
        }
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.next.value == value) {
                currentNode.next = currentNode.next.next;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public boolean removeAt(int index) {
        if (index < 0 || index >= size)
            return false;
        if (index == 0) {
            root = root.next;
            size--;
            return true;
        }
        Node prev = GetNode(index - 1);
        prev.next = prev.next.next;
        size--;
        return true;
    }

    public void removeAll(int value) {
        if (root == null)
            return;
        while (root.value == value) {
            if (root.next == null)
                return;
            root = root.next;
            size--;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            if (currentNode.next.value == value) {
                currentNode.next = currentNode.next.next;
                size--;
                continue;
            }
            currentNode = currentNode.next;
        }
    }

    public void print() {
        Node currentNode = root;
        System.out.print("[ ");
        while (currentNode != null) {
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
        System.out.print("]   size: " + size);
        System.out.println();
    }

    public void revert() {
        if (root == null || root.next == null)
            return;
        if (size == 2) {
            int temp = root.value;
            root.value =  root.next.value;
            root.next.value = temp;
            return;
        }
        revert(null, root);
    }

    private void revert(Node currentNode, Node nextCurrentNode) {
        if (nextCurrentNode.next == null) {
            root = nextCurrentNode;
            root.next = currentNode;
            return;
        }

        Node temp = nextCurrentNode.next;
        nextCurrentNode.next = currentNode;
        revert(nextCurrentNode, temp);
    }

    public void quickSort() {
        quickSort(0, size - 1);
    }

    private void quickSort(int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = this.GetValue((leftMarker + rightMarker) / 2);
        while (leftMarker <= rightMarker) {
            while (this.GetValue(leftMarker) < pivot)
                leftMarker++;
            while (this.GetValue(rightMarker) > pivot)
                rightMarker--;
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker)
                    swap(leftMarker, rightMarker);
                leftMarker++;
                rightMarker--;
            }
        }
        if (leftMarker < rightBorder)
            quickSort(leftMarker, rightBorder);
        if (leftBorder < rightMarker)
            quickSort(leftBorder, rightMarker);
    }

    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size)
            return;
        Node node1 = null, node2 = null, currentNode = root;

        for (int i = 0; currentNode != null; i++) {
            if (node1 == null && index1 == i)
                node1 = currentNode;
            if (node2 == null && index2 == i)
                node2 = currentNode;
            if (node2 != null && node1 != null)
                break;
            currentNode = currentNode.next;
        }
        int temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

    public Node GetNode(int index) {
        if (index < 0 || index >= size)
            return null;
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode;
    }

    public int GetValue(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode.value;
    }

    public int GetSize() {
        return size;
    }

    private class Node {

        int value;
        Node next;

        public Node() {
        }

        public Node(int _value) {
            this.value = _value;
        }

        public Node(int _value, Node _next) {
            this.value = _value;
            this.next = _next;
        }
    }
}