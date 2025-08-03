package ITECC03_04;

import java.util.ArrayList;

public class Receipt {
    String customerName;
    String customerContact;
    ArrayList<Items> purchasedItems;
    float total;
    float promo;

    Receipt(String name, String contact, ArrayList<Items> items, float total, float promo) {
        this.customerName = name;
        this.customerContact = contact;
        this.purchasedItems = new ArrayList<>(items);
        this.total = total;
        this.promo = promo;
    }
}
