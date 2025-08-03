package ITECC03_04;

//importing objects
import java.util.ArrayList;
import java.util.Scanner;


// Main class
public class Main {

    //Instantiation of objects
    public static ArrayList<Items> cart = new ArrayList<>();
    public static ArrayList<Receipt> receiptHistory = new ArrayList<>();
    public static Items item = new Items();
    public static ItemStack viewedItems = new ItemStack();
    public static SupportQueue supportQueue = new SupportQueue();
    static EnglishLanguageClass english = new EnglishLanguageClass();
    static FilipinoLanguageClass fil = new FilipinoLanguageClass();

    //width for centering the display
    public static int width = 210;

    //Main method, entry point of the programs
    public static void main(String[] args) {

        //invocation of method
        runMain();
    }
    //a void method which funtion is to let the user choose
    //their language preference.
    public static void runMain() {
        //instance of scanner
        Scanner scan = new Scanner(System.in);
        //this boolean is used for the flow of the loop
        boolean running = true;
        // a do-while loop is used because it fits the
        // required usage
        do {
            System.out.println();
            printCentered("--Welcome to ClickNGo!-- ", width);
            printCentered("+-------------------------------------------------------------+", Main.width);
            printCentered("Language choices ", width);
            printCentered("[1] English", width);
            printCentered("[2] Filipino", width);
            printCentered("+----------------------------------------------------+", Main.width);
            english.printCenteredInput("Language choices: ", width);
            int languageChoice = scan.nextInt();

            Main.clearConsole();

            // swich-case is used for a much cleaner
            // conditional, and also it has specifics
            switch(languageChoice) {
                case 1: english.runProgram(); break;
                case 2: fil.runProgram(); break;
                case 123: running = false; break;
                default: System.out.println(); printCenteredError("[Invalid choice]", width);
            }
            // the loop will terminate when the user
            // entered '123' in the inputs, as the
            // value of running will be overridden
            // and turn into false
        } while (running);
        // this is invoked to prevent memory leakages
        scan.close();
    }

    // this method is to clear the console
    // and left with the appropriate display
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.printf("%" + padding + "s%s\n", "", text);
    }

    public static void printCenteredError(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.err.printf("%" + padding + "s\u001B[31m%s\u001B[0m\n", "", text);
    }
}