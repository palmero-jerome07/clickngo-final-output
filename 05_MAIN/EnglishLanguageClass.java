package ITECC03_04;

//importing of necessary objects
import java.util.ArrayList;
import java.util.Scanner;


public class EnglishLanguageClass {
    //Global variables
    //Instantiation of objects here
    private final Scanner scan = new Scanner(System.in);
    Main main = new Main();

    //Intitializations of datatype[] variable = {value(s)}; || datatype variable = value; ||just datatype variable;
    private int choice, choiceCategory, choiceProduct, choiceEdit, quantity;
    private int newQuantity, basketNo = 1;
    private char size;
    private boolean running = true;

    //This is array string for main menu of the program (should be same length of characters for this to be aligned when printed)
    private final String[] menu = {"+-------------------------+--- Main Menu ---+-------------------------+",
            "|                    Please enter your choice                         |",
            "|                    1. Display Products                              |",
            "|                    2. Add to Cart                                   |",
            "|                    3. View Cart                                     |",
            "|                    4. Proceed to Checkout                           |",
            "|                    5. Reprint Receipt                               |",
            "|                    6. View Last Viewed Item                         |",
            "|                    7. Submit a ticket                               |",
            "|                    8. Exit                                          |",
            "+------------------------+--------+--------+--------------------------+"};
    // The task of this function is to display the user's choice
    public void runProgram() {
        //clear console first then execute the do-while loop
        Main.clearConsole();
        do {
            System.out.println();
            for (String s : menu) {
                printCentered(s, Main.width);
            }
            printCenteredInput("Enter your choice: ", Main.width-5);
            choice = scan.nextInt();
            System.out.println();
            Main.clearConsole();

            switch (choice) {
                case 1:
                    //invoke method
                    displayMerchandise();
                    break;
                case 2:
                    //invoke method
                    addToCart();
                    break;
                case 3:
                    //invoke method
                    viewCart();
                    break;
                case 4:
                    //invoke method
                    proceed2CheckOut();
                    break;
                case 5:
                    //invoke method
                    printReceipt4PrevOwner();
                    break;
                case 6:
                    //invoke method
                    System.out.println();
                    printStack();
                    break;
                case 7:
                    manageTickets();
                    break;
                case 8:
                    //this block is exit case
                    printCenteredInput("Do you wish to exit? Your cart will be emptied. (Y/N): ", Main.width);
                    char choice = scan.next().charAt(0);
                    if(choice == 'Y' || choice == 'y') {
                        running = false;
                        Main.cart.clear();
                        Main.viewedItems.clear();
                        printCentered("Thank you for using ClickNGo!", Main.width);
                        break;
                    } else if (choice == 'n' || choice == 'N'){
                        running = true;
                        printCentered("[Going back to menu]", Main.width);
                        break;
                    }
                    //If none of the cases didn't initiate, this will trigger
                default:
                    printCenteredError("[Invalid Input. Please try again]", Main.width);
                    System.out.println();
            }

        } while (running);

    }
    // This methods (lines: 85-100 ) is used to print a text to aligned it to your console
    // its parameter is String (for text) and integer (for width or what we call 'padding')
    public void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.printf("%" + padding + "s%s\n", "", text);
    }

    public void printCenteredError(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.err.printf("%" + padding + "s\u001B[31m%s\u001B[0m\n", "", text);
    }

    public void printCenteredInput(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.printf("%" + padding + "s%s", "", text);
    }
    //The function of this method is to display the categories
    private void displayMerchandise() {
        String[] merch = {"Available Mechandise: ",
                " 1. T-Shirt           ",
                " 2. Hat               ",
                " 3. Totebag           ",
                " 4. Plushie           ",
                " 5. Tumbler           "};
        // print merch array
        for (String merches : merch) {
            printCentered(merches, Main.width);
        }
        printCenteredInput(String.format("Select Category%5s: ", ""), Main.width);
        choiceCategory = scan.nextInt();
        scan.nextLine();

        Main.clearConsole();

        switch (choiceCategory) {
            //case 1 is for tshirts
            case 1:
                printCentered("+----------------------------------------------------+", Main.width);
                displayHelper(Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_1, 0 );
                displayHelper(Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_2, 1);
                displayHelper(Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_3, 2);
                printCentered("+----------------------------------------------------+", Main.width);
                selectItemView(Main.item.ShirtsReview, 15, Main.item.tshirt);
                break;
            // this will display hats
            case 2:
                displayHelper(Main.item.hat, Main.item.hatPrice, Main.item.hatStock, 0);
                selectItemView(Main.item.hatReview, 12, Main.item.hat);
                break;
            // this will display totebag
            case 3:
                displayHelper(Main.item.Totebag, Main.item.TotePrice, Main.item.toteStock, 0);
                selectItemView(Main.item.toteReview, 5, Main.item.hat);
                break;
            // this is for plushie toys
            case 4:
                displayHelper(Main.item.Plushie, Main.item.plushiePrice, Main.item.plushieStock, 0);
                selectItemView(Main.item.plushieReview, 7, Main.item.hat);
                break;
            // this is for tumbler
            case 5:
                displayHelper(Main.item.tumbler, Main.item.tumblerPrice, Main.item.tumblerStock, 0);
                selectItemView(Main.item.tumblerReview, 8, Main.item.hat);
                break;
            //default if no cases has been triggered
            default:
                System.out.println();
                printCenteredError("[Invalid Input. Please try again]", Main.width);
                System.out.println();
        }

    }
    private void selectItemView(int[] c_review, int dislikes, String[] category) {
        if(choice == 2) {
            return;
        } else {
            printCenteredInput("Select item reviews: ", Main.width);
            int itemView = scan.nextInt();
            scan.nextLine();

            int choice1 = itemView - 1;

            if(itemView < 1 || itemView > 3) {
                System.out.println();
                printCenteredError("[Invalid Input. Please try again]", Main.width);
                System.out.println();
            } else if(itemView == 1) {
                selectItemViewHelper(c_review, dislikes, category, choice1);
            } else if(itemView == 2) {
                selectItemViewHelper(c_review, dislikes, category, choice1);
            } else if(itemView == 3) {
                selectItemViewHelper(c_review, dislikes, category, choice1);
            }
        }
    }
    private void selectItemViewHelper(int[] c_review, int dislikes, String[] category, int choice1) {
        printCentered("Review for: " + category[choice1], Main.width);
        printCentered("Heart/s: " + c_review[choice1] + " | " + "Dislike/s: " + dislikes, Main.width);
        Main.viewedItems.push(category[choice1]);
    }
    private void printStack() {
        printCentered("+----------------------------------------------------+", Main.width);
        printCentered("[Recently viewed item/s]", Main.width);
        Main.viewedItems.printStackEng();
        printCentered("+----------------------------------------------------+", Main.width);
    }
    // This method is the main displayer of the items
    // It handles Tshirt and non tshirt display
    private void displayHelper(String[] Item, float[] itemPrice, int[] stockItem, int index) {
        System.out.println();
        if(choiceCategory != 1) {
            printCentered("+----------------------------------------------------+", Main.width);
            for(int i = 0; i < stockItem.length; i++) {
                printCentered(String.format("[%d]. %-12s -> %s%6.2f | Qty: %dpc(s)", (i + 1), Item[i], Main.item.pesoSign, itemPrice[i], stockItem[i]), Main.width);
            }
            printCentered("+----------------------------------------------------+", Main.width);
            System.out.println();
        } else {
            printCentered(String.format("[%d]. %-30s - ₱%.2f", (index + 1), Main.item.tshirt[index], Main.item.tshirtPrice[index]), Main.width);
            printCentered(String.format("%10s[Sizes] S = %d | M = %d | L = %d | XL = %d ", "", stockItem[0], stockItem[1], stockItem[2], stockItem[3]), Main.width);
        }
        System.out.println();
    }
    // This method add items to the cart
    private void addToCart() {
        displayMerchandise();

        if (choiceCategory > 5 || choiceCategory < 1) return;
        printCenteredInput("Enter your choice: ", Main.width);
        choiceProduct = scan.nextInt();
        scan.nextLine();

        if (choiceProduct < 1 || choiceProduct > 3) {
            System.out.println();
            printCenteredError("[Invalid Input. Please try again]", Main.width);
            System.out.println();
            addToCart();
            return;
        }

        if(choiceCategory == 1) {
            printCenteredInput("Enter size (S|M|L|X): ", Main.width);
            size = scan.next().toUpperCase().charAt(0);

            if(size != 'S' && size != 'M' && size != 'L' && size != 'X' ) {
                System.out.println();
                printCenteredError("[Invalid Size]", Main.width);
                System.out.println();
                addToCart();
                return;
            }
        }

        printCenteredInput("Enter quantity: ", Main.width);
        quantity = scan.nextInt();
        scan.nextLine();

        switch (choiceCategory) {
            case 1:
                if(choiceProduct == 1) {
                    addToCartHelper(quantity, size, Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_1);
                } else if(choiceProduct == 2) {
                    addToCartHelper(quantity, size, Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_2);
                } else if(choiceProduct == 3) {
                    addToCartHelper(quantity, size, Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_3);
                }
                break;
            case 2:
                addToCartHelper(quantity, '-', Main.item.hat, Main.item.hatPrice, Main.item.hatStock);
                break;
            case 3:
                addToCartHelper(quantity, '-', Main.item.Totebag, Main.item.TotePrice, Main.item.toteStock);
                break;
            case 4:
                addToCartHelper(quantity,'-', Main.item.Plushie, Main.item.plushiePrice, Main.item.plushieStock);
                break;
            case 5:
                addToCartHelper(quantity, '-', Main.item.tumbler, Main.item.tumblerPrice, Main.item.tumblerStock);
                break;
        }
        System.out.println();
    }
    private void addToCartHelper(int quantity, char size , String[] item, float[] itemPrice, int[] itemStock) {
        int choiceProd = choiceProduct - 1;

        // If T-shirt (category 1), handle sizes
        if (choiceCategory == 1) {
            int sizeIndex;
            switch (size) {
                case 'S': sizeIndex = 0; break;
                case 'M': sizeIndex = 1; break;
                case 'L': sizeIndex = 2; break;
                case 'X': sizeIndex = 3; break;
                default:
                    printCenteredError("[Invalid size]", Main.width);
                    return;
            }

            if (quantity > itemStock[sizeIndex] || quantity <= 0) {
                System.out.println();
                Main.clearConsole();
                printCenteredError("Insufficient stock, Available: " + itemStock[sizeIndex] + "pcs", Main.width);
                System.out.println();
                addToCart(); // Go back to re-input
            } else {
                Main.cart.add(new Items(item[choiceProd], size, quantity, itemPrice[choiceProd]));
                itemStock[sizeIndex] -= quantity; // Reduce correct size stock
                System.out.println();
                printCentered("[Item added to cart]", Main.width);
                Main.viewedItems.push(item[choiceProd]);
            }

        } else { // Other categories (no size)
            if (quantity > itemStock[choiceProd] || quantity <= 0) {
                Main.clearConsole();
                printCenteredError("Insufficient stock, Available: " + itemStock[choiceProd] + "pcs", Main.width);
                System.out.println();
                addToCart();
            } else {
                Main.cart.add(new Items(item[choiceProd], '-', quantity, itemPrice[choiceProd]));
                itemStock[choiceProd] -= quantity;
                System.out.println();
                printCentered("[Item added to cart]", Main.width);
                Main.viewedItems.push(item[choiceProd]);
            }
        }
    }
    private void viewCart() {
        if (Main.cart.isEmpty()) {
            printCentered("[No item/s at the moment.]", Main.width);
            System.out.println();
        } else {
            printCentered("Item(s) in cart: " + Main.cart.size(), Main.width);
            printCentered("+----------------------------------------------------+", Main.width);
            for (int i = 0; i < Main.cart.size(); i++) {
                Main.cart.get(i).viewCartHelper();
            }
            printCentered("+----------------------------------------------------+", Main.width);
            System.out.println();

            printCentered("1. Remove basket", Main.width);
            printCentered("2. Edit quantity", Main.width);
            printCentered("3. Back         ", Main.width);
            printCenteredInput("Select action: ", Main.width);
            choiceEdit = scan.nextInt();
            scan.nextLine();

            if (choiceEdit == 1) {
                removeItemInBasket();
            } else if (choiceEdit == 2) {
                editQuantityAndSize();
            } else if (choiceEdit == 3) {
                System.out.println();
                return;
            } else printCenteredError("[Invalid Input-]", Main.width); return;
        }
    }
    private void removeItemInBasket() {
        int choice;
        System.out.println();
        printCentered("To Remove:", Main.width);
        // Display all items in the cart
        printCentered("+----------------------------------------------------+", Main.width);
        for (int i = 0; i < Main.cart.size(); i++) {
            Main.cart.get(i).viewCartHelper(i + 1);
        }
        printCentered("+----------------------------------------------------+", Main.width);
        printCenteredInput("Enter number: ", Main.width);
        choice = scan.nextInt();
        scan.nextLine();

        if (choice < 0 || choice > Main.cart.size()) {
            System.out.println();
            printCenteredError("[Invalid Input]", Main.width);
            System.out.println();
            removeItemInBasket();
        } else {
            Items toRemove = Main.cart.get(choice - 1);
            returnStockForDeletedItem(toRemove);
            Main.cart.remove(toRemove);
            System.out.println();
            printCentered("[Item removed from cart]", Main.width);
            System.out.println();
        }
    }
    private void editQuantityAndSize() {
        System.out.println();
        printCentered("+----------------------------------------------------+", Main.width);
        for (int i = 0; i < Main.cart.size(); i++) {
            Main.cart.get(i).viewCartHelper(i + 1);
        }
        printCentered("+----------------------------------------------------+", Main.width);
        if (Main.cart.size() != 1) {
            System.out.println();
            printCenteredInput("Enter basket no.: ", Main.width);
            basketNo = scan.nextInt();
            scan.nextLine();

            if (basketNo < 1 || basketNo > Main.cart.size()) {
                System.out.println();
                printCenteredError("[Invalid basket number]", Main.width);
                System.out.println();
                editQuantityAndSize();
                return;
            }
        }

        Items selectedItem = Main.cart.get(basketNo - 1);
        String variant = selectedItem.variant;
        int oldQuantity = selectedItem.quantityS;
        char oldSize = selectedItem.sizes;
        char newSize = oldSize;

        System.out.println();
        printCenteredInput("Enter new quantity: ", Main.width);
        newQuantity = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < Main.item.tshirt.length; i++) {
            if (variant.equals(Main.item.tshirt[i])) {
                printCenteredInput("Change size? (Y/N): ", Main.width);
                char change = scan.next().charAt(0);

                if (change == 'y' || change == 'Y') {
                    printCenteredInput("Enter new size: ", Main.width);
                    newSize = Character.toUpperCase(scan.next().charAt(0));
                    int newSizeIndex = getSizeIndex(newSize);

                    if (newSizeIndex == -1) {
                        printCenteredError("[Invalid size input]", Main.width);
                        editQuantityAndSize();
                        return;
                    }

                    selectedItem.setNewSize(newSize);
                    printCentered("[Size updated.]", Main.width);
                }

                // Determine correct design stock array
                int[] sizeStock;
                if (i == 0) {
                    sizeStock = Main.item.tshirtDes_1;
                } else if (i == 1) {
                    sizeStock = Main.item.tshirtDes_2;
                } else {
                    sizeStock = Main.item.tshirtDes_3;
                }

                int oldSizeIndex = getSizeIndex(oldSize);
                int newSizeIndex = getSizeIndex(newSize);

                // Restore old quantity to old size
                sizeStock[oldSizeIndex] += oldQuantity;

                // Check if new stock is enough
                if (newQuantity > sizeStock[newSizeIndex] || newQuantity <= 0) {
                    printCenteredError("[Insufficient stock, available: " + sizeStock[newSizeIndex] + "pcs]", Main.width);
                    sizeStock[oldSizeIndex] -= oldQuantity; // rollback
                    return;
                }

                // Deduct from new size
                sizeStock[newSizeIndex] -= newQuantity;

                selectedItem.setQuantity(newQuantity);
                printCentered("[Quantity updated.]", Main.width);
                return;
            }
        }

        // Non-tshirt items
        for (int i = 0; i < 3; i++) {
            if (variant.equals(Main.item.hat[i])) {
                editQuantityHelper(i, Main.item.hatStock, oldQuantity, newQuantity);
                break;
            } else if (variant.equals(Main.item.Totebag[i])) {
                editQuantityHelper(i, Main.item.toteStock, oldQuantity, newQuantity);
                break;
            } else if (variant.equals(Main.item.Plushie[i])) {
                editQuantityHelper(i, Main.item.plushieStock, oldQuantity, newQuantity);
                break;
            } else if (variant.equals(Main.item.tumbler[i])) {
                editQuantityHelper(i, Main.item.tumblerStock, oldQuantity, newQuantity);
                break;
            }
        }

        selectedItem.setQuantity(newQuantity);
        printCentered("[Quantity updated.]", Main.width);
        System.out.println();
    }

    private int getSizeIndex(char size) {
        size = Character.toUpperCase(size);
        if (size == 'S') return 0;
        if (size == 'M') return 1;
        if (size == 'L') return 2;
        if (size == 'X') return 3;
        return -1;
    }


    private void editQuantityHelper(int index, int[] stockItem, int oldQuantity, int newQuantity) {
        stockItem[index] += oldQuantity;
        if (newQuantity > stockItem[index] || newQuantity <= 0) {
            printCenteredError("[Insufficient stock, available: " + stockItem[index] + "pcs", Main.width);
            stockItem[index] -= oldQuantity;
            return;
        }
        stockItem[index] -= newQuantity;
    }

    private void returnStockForDeletedItem(Items Item) {
        String variant = Item.variant;
        int quantity = Item.quantityS;
        char size = Item.sizes;

        // Determine size index
        int sizeIndex;
        if (size == 'S' || size == 's') sizeIndex = 0;
        else if (size == 'M' || size == 'm') sizeIndex = 1;
        else if (size == 'L' || size == 'l') sizeIndex = 2;
        else if (size == 'X' || size == 'x') sizeIndex = 3;
        else sizeIndex = -1;

        // Check T-shirt designs
        if (variant.equals(Main.item.tshirt[0]) && sizeIndex != -1) {
            Main.item.tshirtDes_1[sizeIndex] += quantity;
            return;
        } else if (variant.equals(Main.item.tshirt[1]) && sizeIndex != -1) {
            Main.item.tshirtDes_2[sizeIndex] += quantity;
            return;
        } else if (variant.equals(Main.item.tshirt[2]) && sizeIndex != -1) {
            Main.item.tshirtDes_3[sizeIndex] += quantity;
            return;
        }

        // Handle other categories
        for (int i = 0; i < Main.item.hat.length; i++) {
            if (variant.equals(Main.item.hat[i])) {
                Main.item.hatStock[i] += quantity;
                return;
            } else if (variant.equals(Main.item.Totebag[i])) {
                Main.item.toteStock[i] += quantity;
                return;
            } else if (variant.equals(Main.item.Plushie[i])) {
                Main.item.plushieStock[i] += quantity;
                return;
            } else if (variant.equals(Main.item.tumbler[i])) {
                Main.item.tumblerStock[i] += quantity;
                return;
            }
        }
    }
    private void manageTickets() {
        int choice;
        boolean running = true;

        //hidden feature, resolve a ticket: #123
        //It deqeues the first ticket and next and so on.

        do {
            printCentered("+----------------------------------------------------+", Main.width);
            printCentered("Customer Support Ticket System", Main.width);
            printCentered("1. Add a Ticket               ", Main.width);
            printCentered("2. View All Tickets           ", Main.width);
            printCentered("3. Return to Main Menu        ", Main.width);
            printCentered("+----------------------------------------------------+", Main.width);
            printCenteredInput("Enter your choice: ", Main.width);
            choice = scan.nextInt();
            scan.nextLine();
            Main.clearConsole();
            switch (choice) {
                case 1:
                    printCenteredInput("Enter customer name: ", Main.width);
                    String name = scan.nextLine();
                    printCenteredInput("Enter issue description: ", Main.width);
                    String issue = scan.nextLine();
                    Main.supportQueue.enqueue(name, issue);
                    break;
                case 2:
                    Main.supportQueue.viewAllTickets();
                    System.out.println();
                    break;
                case 123:
                    Main.supportQueue.dequeue();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    printCentered("[Going back to Main Menu]", Main.width);
                    return;
                default:
                    System.out.println();
                    printCenteredError("Invalid option. Please try again.", Main.width);
                    System.out.println();
            }
        } while (running);
    }
    private void proceed2CheckOut() {
        if (Main.cart.isEmpty()) {
            printCentered("Your cart is empty. Add items before checking out.", Main.width);
            System.out.println();
            return;
        }

        float total = 0f;
        System.out.println();
        printCentered("+-----------------------------+ CHECKOUT SUMMARY +-----------------------------+", Main.width);
        printCentered(String.format("%-4s | %-25s | %-8s | %-10s | %-10s",
                "No.", "Item", "Qty", "Unit Price", "Subtotal"), Main.width);
        printCentered("------+---------------------------+----------+------------+------------", Main.width);

        for (int i = 0; i < Main.cart.size(); i++) {
            Items iItem = Main.cart.get(i);
            float subtotal = iItem.quantityS * iItem.unitPrice;
            total += subtotal;

            printCentered(String.format("%-4d | %-25s | %-8d | ₱%-9.2f | ₱%-9.2f",
                    i + 1, iItem.variant, iItem.quantityS, iItem.unitPrice, subtotal), Main.width);
        }

        printCentered("-----------------------------------------------------------------------", Main.width);
        printCentered(String.format("%45s TOTAL: ₱%.2f", "", total), Main.width);
        System.out.println();

        printCenteredInput("Proceed with checkout? (Y/N): ", Main.width);
        char confirm = scan.next().charAt(0);


        if (confirm == 'y' || confirm == 'Y') {
            scan.nextLine();

            printCenteredInput("Enter your name: ", Main.width);
            String custName = scan.nextLine();

            printCenteredInput("Enter your contact: ", Main.width);
            String custContact = scan.nextLine();


            Main.item.setCustomerName(custName);
            Main.item.setCustomerPhoneNum(custContact);

            printCenteredInput("Enter promo code (enter any if none): ", Main.width);
            String promo = scan.nextLine();

            float promoDiscount = promoDiscount(promo);

            bubbleSortDescending(Main.cart);

            Receipt receipt = new Receipt(custName, custContact, Main.cart, total, promoDiscount);
            Main.receiptHistory.add(receipt);


            Main.clearConsole();
            printReceipt(total, custName, custContact, promoDiscount);
            Main.cart.clear();
            Main.viewedItems.clear();
            printCentered("Thank you for your purchase!", Main.width);
            Main.runMain();
        } else {
            printCentered("Checkout cancelled. [Returning to main menu...]", Main.width);
        }

        System.out.println();
    }

    private void printReceipt(float total, String custName, String custContact, float promo) {
        System.out.println();
        printCentered("+--------------------------------------------------+", Main.width);
        printCentered("|                  CLICK-N-GO RECEIPT              |", Main.width);
        printCentered("+--------------------------------------------------+", Main.width);
        printCentered("Date: " + java.time.LocalDate.now(), Main.width);
        printCentered("Time: " + java.time.LocalTime.now().withNano(0), Main.width);
        printCentered("Customer: " + custName, Main.width);
        printCentered("Contact No: " + custContact, Main.width);
        printCentered("----------------------------------------------------", Main.width);
        printCentered(String.format("%-4s %-24s %-6s %-10s %-10s",
                "No.", "Item", "Qty", "Unit", "Subtotal"), Main.width);
        printCentered("----------------------------------------------------", Main.width);

        for (int i = 0; i < Main.cart.size(); i++) {
            Items iItem = Main.cart.get(i);
            float subtotal = iItem.quantityS * iItem.unitPrice;
            if (iItem.variant.equals(Main.item.tshirt[0]) ||
                    iItem.variant.equals(Main.item.tshirt[1]) ||
                    iItem.variant.equals(Main.item.tshirt[2])) {

                printCentered(String.format("%-4d (%c) %-21s %-5d ₱%-9.2f ₱%-9.2f",
                        i + 1, iItem.sizes, iItem.variant, iItem.quantityS, iItem.unitPrice, subtotal), Main.width);
            } else {
                printCentered(String.format("%-4d %-25s %-5d ₱%-9.2f ₱%-9.2f",
                        i + 1, iItem.variant, iItem.quantityS, iItem.unitPrice, subtotal), Main.width);
            }
        }

        printCentered("----------------------------------------------------", Main.width);
        printCentered(String.format("%45s TOTAL: ₱%.2f", "", total), Main.width);
        if(promo != 1) {
            printCentered(String.format("%30s (W/ PROMO) NEW TOTAL: ₱%.2f", "", total*promo), Main.width);
        }
        printCentered("----------------------------------------------------", Main.width);
        printCentered("            Thank you for shopping with us, " + custName + "!         ", Main.width);
        printCentered("+----------------------------------------------------+", Main.width);
    }

    private void printReceipt4PrevOwner() {

        if(Main.receiptHistory.isEmpty()) {
            printCentered("[No previous customer at the moment]", Main.width);
            System.out.println();
            return;
        }

        printCenteredInput("Enter customer name to reprint receipt: ", Main.width);
        scan.nextLine();
        String searchName = scan.nextLine();

        boolean found = false;
        for (Receipt receipt : Main.receiptHistory) {
            if (receipt.customerName.equalsIgnoreCase(searchName)) {
                found = true;
                printCentered("+--------------------------------------------------+", Main.width);
                printCentered("|            CLICK-N-GO PREVIOUS RECEIPT           |", Main.width);
                printCentered("+--------------------------------------------------+", Main.width);
                printCentered("Date: " + java.time.LocalDate.now(), Main.width);
                printCentered("Time: " + java.time.LocalTime.now().withNano(0), Main.width);
                printCentered("Customer: " + receipt.customerName, Main.width);
                printCentered("Contact No: " + receipt.customerContact, Main.width);
                printCentered("----------------------------------------------------", Main.width);
                printCentered(String.format("%-4s %-24s %-6s %-10s %-10s",
                        "No.", "Item", "Qty", "Unit", "Subtotal"), Main.width);
                printCentered("----------------------------------------------------", Main.width);
                bubbleSortDescending(receipt.purchasedItems);
                for (int i = 0; i < receipt.purchasedItems.size(); i++) {
                    Items item = receipt.purchasedItems.get(i);
                    float subtotal = item.quantityS * item.unitPrice;
                    if (item.variant.equals(Main.item.tshirt[0]) ||
                            item.variant.equals(Main.item.tshirt[1]) ||
                            item.variant.equals(Main.item.tshirt[2])) {

                        printCentered(String.format("%-4d (%c) %-21s %-5d ₱%-9.2f ₱%-9.2f",
                                i + 1, item.sizes, item.variant, item.quantityS, item.unitPrice, subtotal), Main.width);
                    } else {
                        printCentered(String.format("%-4d %-25s %-5d ₱%-9.2f ₱%-9.2f",
                                i + 1, item.variant, item.quantityS, item.unitPrice, subtotal), Main.width);
                    }
                }

                printCentered("----------------------------------------------------", Main.width);
                printCentered(String.format("%45s TOTAL: ₱%.2f", "", receipt.total), Main.width);
                if(receipt.promo != 1) {
                    printCentered(String.format("%30s (W/ PROMO) NEW TOTAL: ₱%.2f", "", receipt.total*receipt.promo), Main.width);
                }
                printCentered("----------------------------------------------------", Main.width);
                printCentered("            Thank you for shopping with us, " + receipt.customerName + "!         ", Main.width);
                printCentered("+----------------------------------------------------+", Main.width);
            }
        }

        if (!found) {
            printCenteredError("No receipt found for: " + searchName, Main.width);
        }
    }
    private float promoDiscount(String promo) {
        switch(promo) {
            case "POGIKAMI123" : return 0.50f; // 50% discount off
            case "TAMANASTOP456" : return 0.20f; // 20% discount off
            case "YESFINALSNA789" : return 0.30f; // 30% discount off
        }
        return 1; // if no case triggered during execution, it will return 1 by default
    }
    // Sorting Algorithm (Bubble Sort) -> to sort the subtotal in descending order
    private void bubbleSortDescending(ArrayList<Items> cart) {
        for (int i = 0; i < cart.size() - 1; i++) {
            for (int j = 0; j < cart.size() - i - 1; j++) {
                float subtotal1 = cart.get(j).quantityS * cart.get(j).unitPrice;
                float subtotal2 = cart.get(j + 1).quantityS * cart.get(j + 1).unitPrice;

                if (subtotal1 < subtotal2) {

                    Items tempor = cart.get(j);
                    cart.set(j, cart.get(j + 1));
                    cart.set(j + 1, tempor);
                }
            }
        }
    }
}