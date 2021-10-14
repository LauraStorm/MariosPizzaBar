import java.util.Scanner;

public class Main {

    /*
    Author: Rasmus & Laura
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int orderIdNumber = 0;
        int userChoice = 0;

        do {

            System.out.println("MARIOS PIZZABAR\n---------------------");
            System.out.println("What would you like to do?");

            System.out.println("• Press 1 - Create Order\n• Press 2 - See all orders\n• Press 3 - Pick up order\n• Press 4 - Show intern statestics\n• Press 5 - Close for today");
            userChoice = scanner.nextInt();

            if (userChoice == 1) {
                System.out.println("1. CREATE ORDER:");
                orderIdNumber++;

                Pizza.showMenuCard();

                Order.getOrder(orderIdNumber);

            } else if (userChoice == 2) {
                System.out.println("2. SEE ALL ORDERS:");

                Order.showOrders();

            } else if (userChoice == 3) {
                System.out.println("3. PICK UP ORDER:");

                Order.removeOrderFromList();

            } else if (userChoice == 4) {
                System.out.println("4. SHOW INTERN STATESTICS:");

                Order.getMostPopularPizza();

            } else if (userChoice == 5) {
                System.out.println("5. CLOSE FOR TODAY:");

            }
        } while (userChoice != 5);

    }
}
