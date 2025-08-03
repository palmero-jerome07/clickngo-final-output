package ITECC03_04;

public class SupportQueue {
    private NodeQ front;
    private NodeQ rear;

    public SupportQueue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }
    public void enqueue(String customerName, String issueDescription) {
        NodeQ newNode = new NodeQ(customerName, issueDescription);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        Main.printCentered("Ticket added: " + customerName + " - " + issueDescription, Main.width);
    }

    public void dequeue() {
        if (isEmpty()) {
            Main.printCentered("No tickets to resolve.", Main.width);
            return;
        }
        Main.printCentered("Resolved ticket: " + front.customerName + " - " + front.issueDescription, Main.width);
        front = front.next;
        if (front == null) {
            rear = null;
        }
    }

    public void viewAllTickets() {
        if (isEmpty()) {
            Main.printCentered("No pending tickets.", Main.width);
            return;
        }
        Main.printCentered("Pending Tickets:", Main.width);
        NodeQ current = front;
        int ticketNumber = 1;
        while (current != null) {
            Main.printCentered(ticketNumber + ". " + current.customerName + " - " + current.issueDescription, Main.width);
            current = current.next;
            ticketNumber++;
        }
    }
}
