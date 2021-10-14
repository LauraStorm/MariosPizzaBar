import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Order {
    //Attributes
    static Scanner scanner = new Scanner(System.in);
    private Pizza[] pizzas;
    private int orderId;
    private String orderName;
    private String pickupTime;
    private static ArrayList<Order> listOfCurrentOrders = new ArrayList<Order>();
    private static int[] todaysPizzaCounter = new int[15];
    private Timestamp timeStamp;

    //Constructor
    public Order(Pizza[] pizzas, int orderId, String orderName, String pickupTime, Timestamp timeStamp) {
        this.pizzas = pizzas;
        this.orderId = orderId;
        this.orderName = orderName;
        this.pickupTime = pickupTime;
        this.timeStamp = timeStamp;
    }


    static Order getOrder(int orderIdNumber) {
        /* Author: Laura */
        //Get number of pizzas to order
        System.out.println("\nHow many pizzas would you like? - please type the number:");
        int pizzaNumbersInOrder = scanner.nextInt();

        //Creating new Pizza Array - That can store chosen pizzas
        Pizza[] pizzasInOrder = new Pizza[pizzaNumbersInOrder];

        /* Author: Simon */
        int i = 0;
        do {
            //Get pizza choice to store in the pizza array
            System.out.println("Please type the pizza number of choice:");
            int pizzaNumberChoice = scanner.nextInt();
            pizzasInOrder[i] = Pizza.getPizza(pizzaNumberChoice);

            //Adding pizza choices to an array (so later we can find the most popular pizza)
            Order.todaysPizzaCounter[pizzaNumberChoice]++;
            i++;

        } while (i < pizzaNumbersInOrder);

        /* Author: Rasmus */
        //Creating the Order object
        System.out.println("What is the order name?");
        String orderName = scanner.next();
        System.out.println("Please type when the wished pickup time is:");
        String pickupTime = scanner.next();

        Order order = new Order(pizzasInOrder, orderIdNumber, orderName, pickupTime, TimeStampExample.getTimeStamp());

        //Arraylist that add the Order(s)
        listOfCurrentOrders.add(order);

        //Print overview of pizzas in the order
        System.out.println("These are the ordered pizza's:");
        for (Pizza pizza : pizzasInOrder) {
            System.out.println(pizza);

        }
        return order;
    }

    /* Author: Laura */
    static void showOrders(){
        for (Order order : listOfCurrentOrders) {
            System.out.println(order);
        }
    }


    /* Authors: Laura & Rasmus */
    static void removeOrderFromList(){
        System.out.println("What order do you want to delete, enter the order ID");

        //show orders before removing an order
        showOrders();

        //Remove order from list if input == orderID
        int orderToRemove = scanner.nextInt();
        listOfCurrentOrders.removeIf(order -> (order.orderId == orderToRemove ));

        //show orders after removing an order
        showOrders();
    }

    /* Author: Simon */
    // found this on : https://stackoverflow.com/questions/22911722/how-to-find-array-index-of-largest-value
    static int getIndexOfLargest( int[] array )
    //this method: finding the index with the most chosen pizza
    {
        if ( array == null || array.length == 0 ) return -1; // null or empty

        int largest = 0;
        for ( int i = 1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest; // position of the first largest found
    }

    /* Author: Simon */
    static void getMostPopularPizza(){
        System.out.println("The most ordered pizza today is:\n" + Pizza.getPizza(getIndexOfLargest(todaysPizzaCounter)));
    }


    //toString
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
