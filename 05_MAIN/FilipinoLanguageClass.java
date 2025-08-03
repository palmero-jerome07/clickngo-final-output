package ITECC03_04;

import java.util.ArrayList;
import java.util.Scanner;

public class FilipinoLanguageClass{


	//Initializations here
	private final Scanner scan = new Scanner(System.in);
	Main main = new Main();
	//   private final ArrayList<Items> cart = new ArrayList<Items>();
	//   private final ArrayList<Receipt> receiptHistory = new ArrayList<>();

	private int choice, choiceCategory, choiceProduct, choiceEdit, quantity;
	private int newQuantity, basketNo = 1;
	private char size;
	private boolean running = true;
	// private int main.width = 150;
	private final String[] menu = {"+-------------------------+--- Pangunahing Menu ---+-------------------------+",
			"|                    Mangyaring ilagay ang iyong pinili                      |",
			"|                    1. Ipakita ang mga Produkto                             |",
			"|                    2. Magdagdag sa Cart                                    |",
			"|                    3. Tingnan ang Cart                                     |",
			"|                    4. Magpatuloy sa Checkout                               |",
			"|                    5. I-reprint ang resibo                                 |",
			"|                    6. Huling tiningtan na item                             |",
			"|                    7. Mag-submit ng ticket                                 |",
			"|                    8. Umalis (Exit)                                        |",
			"+----------------------------+--------+--------+-----------------------------+"};


	public void runProgram() {
		Main.clearConsole();
		do {
			System.out.println();
			for (String s : menu) {
				printCentered(s, Main.width);
			}
			printCenteredInput("Ilagay ang iyong pinili: ", Main.width);
			choice = scan.nextInt();
			System.out.println();
			Main.clearConsole();

			switch (choice) {
				case 1:
					displayMerchandise();
					break;
				case 2:
					addToCart();
					break;
				case 3:
					viewCart();
					break;
				case 4:
					proceed2CheckOut();
					break;
				case 5:
					printReceipt4PrevOwner();
					break;
				case 6:
					System.out.println();
					printStack();
					break;
				case 7:
					manageTickets();
					break;
				case 8:
					printCenteredInput("Gusto mong mag-exit? Ang cart mo ay mawawala. (Y/N): ", Main.width);
					char choice = scan.next().charAt(0);
					if(choice == 'Y' || choice == 'y') {
						running = false;
						Main.cart.clear();
						Main.viewedItems.clear();
						printCentered("Thank you for using ClickNGo!", Main.width);
						break;
					} else if (choice == 'n' || choice == 'N'){
						running = true;
						printCentered("[Muling babalik sa menu]", Main.width);
						break;
					}
				default:
					printCenteredError("[Di-wastong Input. Pakisubukang muli.]", Main.width);
					System.out.println();
			}


		} while (running);

	}

	private void printCentered(String text, int width) {
		int padding = (width - text.length()) / 2;
		System.out.printf("%" + padding + "s%s\n", "", text);
	}

	private void printCenteredError(String text, int width) {
		int padding = (width - text.length()) / 2;
		System.err.printf("%" + padding + "s\u001B[31m%s\u001B[0m\n", "", text);
	}

	private void printCenteredInput(String text, int width) {
		int padding = (width - text.length()) / 2;
		System.out.printf("%" + padding + "s%s", "", text);
	}

	private void displayMerchandise() {
		String[] merch = {"Mga Available na Produkto: ",
				" 1. T-Shirt           ",
				" 2. Hat               ",
				" 3. Totebag           ",
				" 4. Plushie           ",
				" 5. Tumbler           "};

		for (String merches : merch) {
			printCentered(merches, Main.width);
		}
		printCenteredInput(String.format("Pumili ng kategorya%5s: ", ""), Main.width);
		choiceCategory = scan.nextInt();
		scan.nextLine();

		Main.clearConsole();

		switch (choiceCategory) {
			case 1:
				printCentered("+----------------------------------------------------+", Main.width);
				displayHelper(Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_1, 0 );
				displayHelper(Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_2, 1);
				displayHelper(Main.item.tshirt, Main.item.tshirtPrice, Main.item.tshirtDes_3, 2);
				printCentered("+----------------------------------------------------+", Main.width);
				selectItemView(Main.item.ShirtsReview, 15, Main.item.tshirt);
				break;
			case 2:
				displayHelper(Main.item.hat, Main.item.hatPrice, Main.item.hatStock, 0);
				selectItemView(Main.item.hatReview, 12, Main.item.hat);
				break;
			case 3:
				displayHelper(Main.item.Totebag, Main.item.TotePrice, Main.item.toteStock, 0);
				selectItemView(Main.item.toteReview, 5, Main.item.hat);
				break;
			case 4:
				displayHelper(Main.item.Plushie, Main.item.plushiePrice, Main.item.plushieStock, 0);
				selectItemView(Main.item.plushieReview, 7, Main.item.hat);
				break;
			case 5:
				displayHelper(Main.item.tumbler, Main.item.tumblerPrice, Main.item.tumblerStock, 0);
				selectItemView(Main.item.tumblerReview, 8, Main.item.hat);
				break;
			default:
				System.out.println();
				printCenteredError("[Invalid Input. Please try again]", Main.width);
				System.out.println();

		}
		//If 1 yung sinelect niya, ang gagamitin niya na array para sa stocks ay alinsunod sa 1 na napili o "T-shirt Design 1"
	}
	private void selectItemView(int[] c_review, int dislikes, String[] category) {
		if(choice == 2) {
			return;
		} else {
			printCenteredInput("Silipin ang reviews (1-3): ", Main.width);
			int itemView = scan.nextInt();
			scan.nextLine();

			int choice1 = itemView - 1;

			if(itemView < 1 || itemView > 3) {
				System.out.println();
				printCenteredError("[Di-wastong Input. Pakisubukang muli.]", Main.width);
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
	private void selectItemViewHelper(int[] c_review, int dislike, String[] category, int choice1) {
		printCentered("Review para sa: " + category[choice1], Main.width);
		printCentered("Heart/s: " + c_review[choice1] + " | Dislike/s: " + dislike, Main.width);
		Main.viewedItems.push(category[choice1]);
	}
	private void printStack() {
		printCentered("+----------------------------------------------------+", Main.width);
		printCentered("[Huling tinignan na item]", Main.width);
		Main.viewedItems.printStackFil();
		printCentered("+----------------------------------------------------+", Main.width);
	}
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
	private void addToCart() {
		displayMerchandise();

		if (choiceCategory > 5 || choiceCategory < 1) return;
		printCenteredInput("Ipasok ang iyong pinili: ", Main.width);
		choiceProduct = scan.nextInt();
		scan.nextLine();

		if (choiceProduct < 1 || choiceProduct > 3) {
			printCenteredError("[Di-wastong Input. Pakisubukang muli.]", Main.width);
			return;
		}

		if(choiceCategory == 1) {
			printCenteredInput("Ipasok ang sukat (S|M|L|X): ", Main.width);
			size = scan.next().toUpperCase().charAt(0);

			if(size != 'S' && size != 'M' && size != 'L' && size != 'X' ) {
				System.out.println();
				printCenteredError("[Di-wastong Sukat]", Main.width);
				System.out.println();
				addToCart();
			}
		}

		printCenteredInput("Ipasok ang dami: ", Main.width);
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
					printCenteredError("[Maling size]", Main.width);
					return;
			}

			if (quantity > itemStock[sizeIndex] || quantity <= 0) {
				System.out.println();
				Main.clearConsole();
				printCenteredError("Kulang ang stock, Available: " + itemStock[sizeIndex] + " piraso", Main.width);
				System.out.println();
				addToCart(); // Go back to re-input
			} else {
				Main.cart.add(new Items(item[choiceProd], size, quantity, itemPrice[choiceProd]));
				itemStock[sizeIndex] -= quantity; // Reduce correct size stock
				System.out.println();
				printCentered("[Nagdagdag ng item sa cart]", Main.width);
				Main.viewedItems.push(item[choiceProd]);
			}

		} else { // Other categories (no size)
			if (quantity > itemStock[choiceProd] || quantity <= 0) {
				Main.clearConsole();
				printCenteredError("Kulang ang stock, Available: " + itemStock[choiceProd] + " piraso", Main.width);
				System.out.println();
				addToCart();
			} else {
				Main.cart.add(new Items(item[choiceProd], '-', quantity, itemPrice[choiceProd]));
				itemStock[choiceProd] -= quantity;
				System.out.println();
				printCentered("[Nagdagdag ng item sa cart]", Main.width);
				Main.viewedItems.push(item[choiceProd]);
			}
		}
	}
	private void viewCart() {
		if (Main.cart.isEmpty()) {
			printCentered("[Walang item sa ngayon.]", Main.width);
			System.out.println();
		} else {
			printCentered("Item(s) in cart: " + Main.cart.size(), Main.width);
			printCentered("----------------------------------------------------", Main.width);
			for (int i = 0; i < Main.cart.size(); i++) {
				Main.cart.get(i).viewCartHelper();
			}
			printCentered("----------------------------------------------------", Main.width);
			System.out.println();

			printCentered("1. Alisin ang basket", Main.width);
			printCentered("2. I-edit ang dami  ", Main.width);
			printCentered("3. Bumalik         ", Main.width);
			printCenteredInput("Pumili ng aksyon: ", Main.width);
			choiceEdit = scan.nextInt();
			scan.nextLine();

			if (choiceEdit == 1) {
				removeItemInBasket();
			} else if (choiceEdit == 2) {
				editQuantityAndSize();
			} else if (choiceEdit == 3) {
				System.out.println();
				return;
			} else printCenteredError("[Di-wastong Input]", Main.width);
		}
		//return;
	}
	private void removeItemInBasket() {
		int choice;
		System.out.println();
		printCentered("Alisin:", Main.width);
		// Display all items in the cart
		printCentered("----------------------------------------------------", Main.width);
		for (int i = 0; i < Main.cart.size(); i++) {
			Main.cart.get(i).viewCartHelper(i + 1);
		}
		printCentered("----------------------------------------------------", Main.width);
		printCenteredInput("Ilagay ang numero: ", Main.width);
		choice = scan.nextInt();
		scan.nextLine();

		if (choice < 0 || choice > Main.cart.size()) {
			System.out.println();
			printCenteredError("[Maling Input]", Main.width);
			System.out.println();
			removeItemInBasket();
		} else {
			Items toRemove = Main.cart.get(choice - 1);
			returnStockForDeletedItem(toRemove);
			Main.cart.remove(toRemove);
			System.out.println();
			printCentered("[Item ay naalis sa cart]", Main.width);
			System.out.println();
		}
	}
	private void editQuantityAndSize() {
		System.out.println();
		printCentered("----------------------------------------------------", Main.width);
		for (int i = 0; i < Main.cart.size(); i++) {
			Main.cart.get(i).viewCartHelper(i + 1);
		}
		printCentered("----------------------------------------------------", Main.width);
		if (Main.cart.size() != 1) {
			System.out.println();
			printCenteredInput("Ilagay ang numero ng basket: ", Main.width);
			basketNo = scan.nextInt();
			scan.nextLine();

			if (basketNo < 1 || basketNo > Main.cart.size()) {
				System.out.println();
				printCenteredError("[Maling numero ng basket]", Main.width);
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
		printCenteredInput("Ilagay ang bagong dami: ", Main.width);
		newQuantity = scan.nextInt();
		scan.nextLine();

		for (int i = 0; i < Main.item.tshirt.length; i++) {
			if (variant.equals(Main.item.tshirt[i])) {
				printCenteredInput("Papalitan ang size? (Y/N): ", Main.width);
				char change = scan.next().charAt(0);

				if (change == 'y' || change == 'Y') {
					printCenteredInput("Ilagay ang bagong size: ", Main.width);
					newSize = Character.toUpperCase(scan.next().charAt(0));
					int newSizeIndex = getSizeIndex(newSize);

					if (newSizeIndex == -1) {
						printCenteredError("[Maling input ng size]", Main.width);
						editQuantityAndSize();
						return;
					}

					selectedItem.setNewSize(newSize);
					printCentered("[Na-update ang size]", Main.width);
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
					printCenteredError("[Kulang ang stock, available: " + sizeStock[newSizeIndex] + " piraso]", Main.width);
					sizeStock[oldSizeIndex] -= oldQuantity; // rollback
					return;
				}

				// Deduct from new size
				sizeStock[newSizeIndex] -= newQuantity;

				selectedItem.setQuantity(newQuantity);
				printCentered("[Na-update ang dami]", Main.width);
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
		printCentered("[Na-update ang dami]", Main.width);
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
			printCenteredError("[Kulang ang stock, available: " + stockItem[index] + " piraso", Main.width);
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
			printCentered("Walang laman ang iyong cart. Magdagdag ng mga item bago mag-check out.", Main.width);
			System.out.println();
			return;
		}

		float total = 0f;
		System.out.println();
		printCentered("+-----------------------------+ BUOD NG CHECKOUT +-----------------------------+", Main.width);
		printCentered(String.format("%-4s | %-25s | %-8s | %-10s | %-10s",
				"No.", "Item", "Qty", "Unit Price", "Subtotal"), Main.width);
		printCentered("------+---------------------------+----------+------------+------------", Main.width);
		bubbleSortDescending(Main.cart);
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

		printCenteredInput("Magpatuloy sa pag-checkout? (Y/N): ", Main.width);
		char confirm = scan.next().charAt(0);


		if (confirm == 'y' || confirm == 'Y') {
			scan.nextLine();

			printCenteredInput("Ilagay ang iyong pangalan: ", Main.width);
			String custName = scan.nextLine();

			printCenteredInput("Ilagay ang iyong contact: ", Main.width);
			String custContact = scan.nextLine();


			Main.item.setCustomerName(custName);
			Main.item.setCustomerPhoneNum(custContact);

			printCenteredInput("Ilagay ang promo code (ilagay ang anuman kung wala): ", Main.width);
			String promo = scan.nextLine();

			float promoDiscount = promoDiscount(promo);

			bubbleSortDescending(Main.cart);

			Receipt receipt = new Receipt(custName, custContact, Main.cart, total, promoDiscount);
			Main.receiptHistory.add(receipt);


			Main.clearConsole();
			printReceipt(total, custName, custContact, promoDiscount);
			Main.cart.clear();
			Main.viewedItems.clear();
			printCentered("Salamat sa iyong pagbili!", Main.width);
			Main.runMain();
		} else {
			printCentered("Kinansela ang checkout. [Bumalik sa pangunahing menu...]", Main.width);
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
			if(iItem.variant.equals(Main.item.tshirt[0]) || iItem.variant.equals(Main.item.tshirt[1]) || iItem.variant.equals(Main.item.tshirt[2])) {
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
		printCentered("          Salamat sa pamimili sa amin, " + custName + "!         ", Main.width);
		printCentered("+----------------------------------------------------+", Main.width);
	}

	private void printReceipt4PrevOwner() {

		if(Main.receiptHistory.isEmpty()) {
			printCentered("[Walang dating customer sa ngayon]", Main.width);
			System.out.println();
			return;
		}

		printCenteredInput("Ilagay ang pangalan ng customer para i-print muli ang resibo: ", Main.width);
		scan.nextLine();
		String searchName = scan.nextLine();

		boolean found = false;
		for (Receipt receipt : Main.receiptHistory) {
			if (receipt.customerName.equalsIgnoreCase(searchName)) {
				found = true;
				printCentered("+--------------------------------------------------+", Main.width);
				printCentered("|            CLICK-N-GO NAKARAANG RECEIPT           |", Main.width);
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
					if(item.variant.equals(Main.item.tshirt[0]) || item.variant.equals(Main.item.tshirt[1]) || item.variant.equals(Main.item.tshirt[2])) {
						printCentered(String.format("%-4d (%c) %-24s %-6d ₱%-9.2f ₱%-9.2f",
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
				printCentered("          Salamat sa pamimili sa amin, " + receipt.customerName + "!         ", Main.width);
				printCentered("+----------------------------------------------------+", Main.width);
			}
		}

		if (!found) {
			printCenteredError("No receipt found for: " + searchName, Main.width);
		}
	}
	private float promoDiscount(String promo) {

		switch(promo) {
			case "POGIKAMI123" : return 0.50f;
			case "TAMANASTOP456" : return 0.20f;
			case "YESFINALSNA789" : return 0.30f;
		}
		return 1;
	}
	// Sorting Algorithm (Bubble Sort) -> to sort the subtotal in descending order
	public void bubbleSortDescending(ArrayList<Items> cart) {
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