import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your database manager.\n");
        while (true){
            System.out.println("1.Add/change product.");
            System.out.println("2.Remove product from database.");
            System.out.println("3.Find product.");
            System.out.println("4.Print database.");
            System.out.println("5.Get by price.");
            System.out.println("6.Exit.");

            try {
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("Enter id, label and price.");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        String label = scanner.nextLine();
                        int price = scanner.nextInt();
                        database.addToDatabase(id, new Product(label, price));
                        break;
                    case 2:
                        System.out.println("Enter id.");
                        database.remove(scanner.nextInt());
                        break;
                    case 3:
                        System.out.println("Enter id.");
                        database.findProduct(scanner.nextInt());
                        break;
                    case 4:
                        database.printDatabase();
                        break;
                    case 5:
                        System.out.println("Do you want to find products with value 1.lower, 2.equal, 3.higher?");
                        int option;
                        option = scanner.nextInt();
                        while (option != 1 && option != 2 && option != 3) {
                            System.out.println("Please enter correct input.");
                            option = scanner.nextInt();
                        }

                        System.out.println("What is the threshold?");
                        int threshold = scanner.nextInt();

                        switch (option) {
                            case 1 -> database.printPriceLower(threshold);
                            case 2 -> database.printPriceEqual(threshold);
                            case 3 -> database.printPriceHigher(threshold);
                        }
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }
                System.out.println();
            } catch (InputMismatchException inputMismatchException){
                incorrectInputMessage(scanner);
            }
        }
    }

    public static void incorrectInputMessage(Scanner scanner){
        String incorrectInput = scanner.next();
        System.out.println("Erroneous data: " + incorrectInput + "\n");
    }
}
