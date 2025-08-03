package ITECC03_04;

public class NodeQ {
    String customerName;
    String issueDescription;
    NodeQ next;

    public NodeQ(String customerName, String issueDescription) {
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.next = null;
    }
}
