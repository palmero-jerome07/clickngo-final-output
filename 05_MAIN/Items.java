package ITECC03_04;

public class Items {

	// Variants
	char[] size = {'S', 'M', 'L', 'X'};

	String[] tshirt    = {"T-shirt Design 1", "T-shirt Design 2", "T-shirt Design 3"};
	String[] hat       = {"Beanie      ", "Cap         ", "Trapper Hat "};
	String[] Totebag   = {"Red   ", "Blue  ", "Green "};
	String[] Plushie   = {"Bear   ", "Rabbit ", "Cat    "};
	String[] tumbler   = {"Silver ", "Black  ", "Gold   "};


	// Prices (in PHP)
	float[] tshirtPrice     = {499.00f, 599.00f, 699.00f};  // tshirt bai
	float[] hatPrice        = {199.00f, 259.00f, 299.00f};  // Caps and hats bai
	float[] TotePrice       = {149.00f, 199.00f, 249.00f};  // Tote Bags bai
	float[] plushiePrice    = {299.00f, 349.00f, 399.00f};  // Plush Toys bai
	float[] tumblerPrice    = {249.00f, 299.00f, 349.00f};  // Tumblers bai

	// Quantity per size/design
	int[] tshirtDes_1   = {10, 11, 12, 13};  // S, M, L, XL
	int[] tshirtDes_2   = {14, 15, 16, 17};
	int[] tshirtDes_3   = {18, 19, 20, 21};

	int[] hatStock      = {20, 20, 20};      // 3 types of hats
	int[] toteStock     = {15, 15, 15};      // 3 types of tote bags
	int[] plushieStock  = {10, 10, 10};      // 3 plushie designs
	int[] tumblerStock  = {20, 20, 20};      // 3 tumbler types



	int[] ShirtsReview  = {153, 124, 753};   // hearts
	int[] hatReview     = {234, 547, 536};   // hearts
	int[] toteReview    = {541, 641, 121}; 	 // hearts
	int[] plushieReview = {145, 122, 245};	 // hearts
	int[] tumblerReview = {123, 456, 789};	 // hearts


	//For class constructor
	String customerName, customerPhoneNum;
	String variant;
	char   sizes;
	float  totalPrice = 0;
	float  price, unitPrice;
	int    quantityS;

	//formattings
	final public char pesoSign = '\u20B1';
	final int width = 150;


	public Items(String variant, char size, int quantityS, float unitPrice) {
		this.variant = variant;
		this.sizes = size;
		this.quantityS = quantityS;
		this.unitPrice = unitPrice;
		this.totalPrice = quantityS * unitPrice;
	}
	public Items() { };

	public void setSize(char newSize) {
		this.sizes = newSize;
	}

	public void setQuantity(int quantityS) {
		this.quantityS = quantityS;
		this.totalPrice = this.unitPrice * quantityS;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerPhoneNum(String phoneNum) {
		this.customerPhoneNum = phoneNum;
	}

	public void setNewSize(char size) {
		this.sizes = size;
	}

	public void viewCartHelper() {
		String formatted;
		if (sizes == '-') {
			formatted = String.format("%2d X %-10s | Price: ₱%6.2f", quantityS, variant, totalPrice);
		} else {
			formatted = String.format("%2d X %-8s (Size: %c) | Price: ₱%6.2f", quantityS, variant, sizes, totalPrice);
		}
		printCentered("|" + formatted + " |", Main.width);
	}
	public void viewCartHelper(int index) {
		String formatted;
		if (sizes == '-') {
			formatted = String.format("%2d X %-10s | Price: ₱%6.2f", quantityS, variant, totalPrice);
		} else {
			formatted = String.format("%2d X %-8s (Size: %c) | Price: ₱%6.2f", quantityS, variant, sizes, totalPrice);
		}
		printCentered("[" + index + "] |" + formatted + " |", Main.width);
	}

	private void printCentered(String text, int width) {
		int padding = (width - text.length()) / 2;
		System.out.printf("%" + padding + "s%s\n", "", text);
	}



}
