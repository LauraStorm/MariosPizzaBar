import jdk.jfr.Timespan;

import java.sql.Timestamp;
import java.util.*;

public class Order {
    //Attributes
    static Scanner scanner = new Scanner(System.in);
    private Pizza[] pizzas;
    private int orderId;
    private String orderName;
    private int pickupTime;
    private static ArrayList<Order> listOfCurrentOrders = new ArrayList<Order>();
    private static int[] todaysPizzaCounter = new int[15];
    private Timestamp timeStamp;

    //Constructor
    public Order(Pizza[] pizzas, int orderId, String orderName, int pickupTime, Timestamp timeStamp) {
        this.pizzas = pizzas;
        this.orderId = orderId;
        this.orderName = orderName;
        this.pickupTime = pickupTime;
        this.timeStamp = timeStamp;
    }

    //Methods

    /* Author: Rasmus */
    //her fra fhttps://www.javaprogramto.com/2020/04/java-add-minutes-to-date.html
    public Date getPickupTime(){
        //metode til at finde datoen plus den afhentningstiden(pickuptime)

        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis();
        Date timeAfterAddingPickUpTime = new Date(timeInSecs + (pickupTime * 60 * 1000));

        return timeAfterAddingPickUpTime;
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
        System.out.println("When can you pick up the order? - type in minutes:");
        int pickupTime = scanner.nextInt();

        Order order = new Order(pizzasInOrder, orderIdNumber, orderName, pickupTime, TimeStampExample.getTimeStamp());

        //Arraylist that add the Order(s)
        listOfCurrentOrders.add(order);

        //Print overview of pizzas in the order
        System.out.println("These are the ordered pizzas:");
        for (Pizza pizza : pizzasInOrder) {
            System.out.println(pizza);

        }
        return order;
    }

    /* Author: Laura */
    static void showOrders(){

        if (listOfCurrentOrders.size() == 0){
            System.out.println("There are no orders!");
        }

        /* Author: Rasmus */
        Comparator<Order> orderSorting = Comparator.comparing(Order :: getPickupTime);
        listOfCurrentOrders.sort(orderSorting);

        //printer ordrerne i r??kkef??lge + afhentningstidspunktet efter ordre-objektet
        for (Order e : listOfCurrentOrders) {
            System.out.println(e + "\nPickupTime: " + e.getPickupTime());

        }
    }


    /* Authors: Laura & Rasmus */
    static void removeOrderFromList(){
        System.out.println("What order do you want to remove? - enter the order ID:");

        //show orders before removing an order
        showOrders();

        //Remove order from list if input == orderID
        int orderToRemove = scanner.nextInt();
        listOfCurrentOrders.removeIf(order -> (order.orderId == orderToRemove));

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
        System.out.println("The most ordered pizza today is: " + Pizza.getPizza(getIndexOfLargest(todaysPizzaCounter)));
    }


    /* Author: Rasmus */
    //toString
    @Override
    public String toString() {
        String pizzasString ="";
        for (int i = 0; i < pizzas.length; i++) {
            pizzasString += "\n" + pizzas[i].toStringPizzaOrder();
        }
        return "\n" + this.orderId + ". Order "  +
                "= " +
                " Id: " + orderId +
                ", Name: " + orderName + pizzasString;
    }
}
