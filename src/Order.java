import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Order {
    static Scanner scanner = new Scanner(System.in);
    private Pizza[] pizzas;
    private int orderId;
    private String orderName;
    private String pickupTime;
    private static ArrayList<Order> listOfCurrentOrders = new ArrayList<Order>();
    private static int[] todaysPizzaCounter = new int[15];
    private Timestamp timeStamp;

    public Order(Pizza[] pizzas, int orderId, String orderName, String pickupTime, Timestamp timeStamp) {
        this.pizzas = pizzas;
        this.orderId = orderId;
        this.orderName = orderName;
        this.pickupTime = pickupTime;
        this.timeStamp = timeStamp;
    }

    static Order getdOrder(int orderIdNumber, int input) {

        System.out.println("How many pizzas would you like? - please type a number:");
        int pizzaNumbersInOrder = scanner.nextInt();

        Pizza[] pizzasInOrder = new Pizza[pizzaNumbersInOrder];
        int i = 0;
        do {
            System.out.println("Please type the pizza number of choice:");
            int pizzaNumberChoice = scanner.nextInt();
            pizzasInOrder[i] = Pizza.getPizza(pizzaNumberChoice);
            Order.todaysPizzaCounter[pizzaNumberChoice]++;
            i++;
        } while (i < pizzaNumbersInOrder);
        System.out.println("What is the order name?");
        String orderName = scanner.next();
        System.out.println("Please type when the wished pickup time is:");
        String pickupTime = scanner.next();
        Order order = new Order(pizzasInOrder, orderIdNumber, orderName, pickupTime, TimeStampExample.getTimeStamp());

        listOfCurrentOrders.add(order);

        System.out.println("These are the ordered pizza's:");
        for (Pizza pizza : pizzasInOrder) {
            System.out.println(pizza);

        }
        return order;
    }

    static void showOrders(){
        for (Order e : listOfCurrentOrders) {
            System.out.println(e);
        }
    }

    static void removeOrderFromList(){

        System.out.println("What order do you want to delete, enter the order ID");

        showOrders();

        int orderToRemove = scanner.nextInt();

        listOfCurrentOrders.removeIf(order -> (order.orderId == orderToRemove ));

        showOrders();
    }

    static void getMostPopularPizza(){
        System.out.println("The most ordered pizza today is: " + Pizza.getPizza(getIndexOfLargest(todaysPizzaCounter)));
    }
// found this on : https://stackoverflow.com/questions/22911722/how-to-find-array-index-of-largest-value
    static int getIndexOfLargest( int[] array )
    {
        if ( array == null || array.length == 0 ) return -1; // null or empty

        int largest = 0;
        for ( int i = 1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest; // position of the first largest found
    }

    @Override
    public String toString() {
        return this.orderId + ". Order "  +
                "= " + Arrays.toString(pizzas) +
                ", orderId = " + orderId +
                ", orderName = '" + orderName + '\'' +
                ", pickupTime = " + pickupTime +
                ", timeStamp=" + timeStamp ;
    }
}
