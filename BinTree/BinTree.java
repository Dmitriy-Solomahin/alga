package BinTree;

import java.util.ArrayList;
import java.util.List;

public class BinTree<T extends Comparable<T>> {

    Node root;
    private int size;

    public boolean add(T value){
        if (root == null){
            root = new Node(value);
            size = 1;
            return true;
        }else if (addNode(root, value) != null){
            size ++;
            return true;
        }return false;
    }

    private Node addNode(Node node, T value){
        if (node.value.compareTo(value) == 0) 
            return null;
        if (node.value.compareTo(value) > 0) { // left
            if(node.left == null){
                node.left = new Node(value);
                return node.left;
            }
            return addNode(node.left, value);
        }else{ // right
            if(node.right == null){
                node.right = new Node(value);
                return node.right;
            }
            return addNode(node.right, value);
        }
    }

    public boolean remove(T value){
        Node deleteNode = root;
        while (deleteNode != null){
            if (deleteNode.value.compareTo(value) == 0){
                if (deleteNode.right != null){
                    Node currentNode = deleteNode.right;
                    Node previousNode = deleteNode;
                    while (currentNode.left != null) {
                        previousNode = currentNode;
                        currentNode = currentNode.left;
                    }
                    previousNode.left = currentNode.right;
                    deleteNode.value = currentNode.value;
                    currentNode = null;
                    return true;
                }
                else if (deleteNode.left != null){
                    Node currentNode = deleteNode.left;
                    Node previousNode = deleteNode;
                    while (currentNode.right != null){
                        previousNode = currentNode;
                        currentNode = currentNode.right;
                    }
                    previousNode.right = currentNode.left;
                    deleteNode.value = currentNode.value;
                    currentNode = null;
                    return true;
                }
                else{
                    deleteNode = null;
                    return true;
                }

            }
            else if (deleteNode.value.compareTo(value)>0)
                deleteNode = deleteNode.left;
            else
                deleteNode = deleteNode.right;
        }
        return false;
    }

    private class Node{
        T value;
        Node left;
        Node right;

        Node(){}
        Node(T value){this.value = value;}
    }

    private class PrintNode {
        Node node;
        String str;
        int depth;

        public PrintNode() {
            node = null;
            str = " ";
            depth = 0;
        }

        public PrintNode(Node node) {
            depth = 0;
            this.node = node;
            this.str = node.value.toString();
        }
    }

    public void print() {

        int maxDepth = maxDepth() + 3;
        int nodeCount = nodeCount(root, 0);
        int width = 50;//maxDepth * 4 + 2;
        int height = nodeCount * 5;
        List<List<PrintNode>> list = new ArrayList<List<PrintNode>>();
        for (int i = 0; i < height; i++) /*РЎРѕР·РґР°РЅРёРµ СЏС‡РµРµРє РјР°СЃСЃРёРІР°*/ {
            ArrayList<PrintNode> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new PrintNode());
            }
            list.add(row);
        }

        list.get(height / 2).set(0, new PrintNode(root));
        list.get(height / 2).get(0).depth = 0;

        for (int j = 0; j < width; j++)  /*РџСЂРёРЅС†РёРї Р·Р°РїРѕР»РЅРµРЅРёСЏ*/ {
            for (int i = 0; i < height; i++) {
                PrintNode currentNode = list.get(i).get(j);
                if (currentNode.node != null) {
                    currentNode.str = currentNode.node.value.toString();
                    if (currentNode.node.left != null) {
                        int in = i + (maxDepth / (int) Math.pow(2, currentNode.depth));
                        int jn = j + 3;
                        printLines(list, i, j, in, jn);
                        list.get(in).get(jn).node = currentNode.node.left;
                        list.get(in).get(jn).depth = list.get(i).get(j).depth + 1;

                    }
                    if (currentNode.node.right != null) {
                        int in = i - (maxDepth / (int) Math.pow(2, currentNode.depth));
                        int jn = j + 3;
                        printLines(list, i, j, in, jn);
                        list.get(in).get(jn).node = currentNode.node.right;
                        list.get(in).get(jn).depth = list.get(i).get(j).depth + 1;
                    }

                }
            }
        }
        for (int i = 0; i < height; i++) /*Р§РёСЃС‚РєР° РїСѓСЃС‚С‹С… СЃС‚СЂРѕРє*/ {
            boolean flag = true;
            for (int j = 0; j < width; j++) {
                if (list.get(i).get(j).str != " ") {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.remove(i);
                i--;
                height--;
            }
        }

        for (var row : list) {
            for (var item : row) {
                System.out.print(item.str + " ");
            }
            System.out.println();
        }
    }

    private void printLines(List<List<PrintNode>> list, int i, int j, int i2, int j2) {
        if (i2 > i) // РРґС‘Рј РІРЅРёР·
        {
            while (i < i2) {
                i++;
                list.get(i).get(j).str = "|";
            }
            list.get(i).get(j).str = "\\";
            while (j < j2) {
                j++;
                list.get(i).get(j).str = "-";
            }
        } else {
            while (i > i2) {
                i--;
                list.get(i).get(j).str = "|";
            }
            list.get(i).get(j).str = "/";
            while (j < j2) {
                j++;
                list.get(i).get(j).str = "-";
            }
        }
    }

    public int maxDepth() {
        return maxDepth2(0, root);
    }

    private int maxDepth2(int depth, Node node) {
        depth++;
        int left = depth;
        int right = depth;
        if (node.left != null)
            left = maxDepth2(depth, node.left);
        if (node.right != null)
            right = maxDepth2(depth, node.right);
        return left > right ? left : right;
    }

    private int nodeCount(Node node, int count) {
        if (node != null) {
            count++;
            return count + nodeCount(node.left, 0) + nodeCount(node.right, 0);
        }
        return count;
    }

    enum Color {black, red}
}

