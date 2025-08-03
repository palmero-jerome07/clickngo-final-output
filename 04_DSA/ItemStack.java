package ITECC03_04;

public class ItemStack {
    private Node top;

    public void push(String item) {
        Node newNode = new Node(item);
        newNode.next = top;
        top = newNode;
    }
    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
    }

    public void printStackEng() {
        if (isEmpty()) {
            Main.printCentered("[No recently viewed items]", Main.width);
            return;
        }

        Node current = top;
        int count = 1;
        while (current != null) {
            Main.printCentered(count + ". " + current.data, Main.width);
            current = current.next;
            count++;
        }
    }
    public void printStackFil() {
        if (isEmpty()) {
            Main.printCentered("[Wala pang tinitingnan na item]", Main.width);
            return;
        }

        Node current = top;
        int count = 1;
        while (current != null) {
            Main.printCentered(count + ". " + current.data, Main.width);
            current = current.next;
            count++;
        }
    }
}
