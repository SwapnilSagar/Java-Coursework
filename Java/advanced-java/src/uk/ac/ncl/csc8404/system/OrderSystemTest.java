package uk.ac.ncl.csc8404.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.csc8404.model.*;
import uk.ac.ncl.csc8404.order.*;
import uk.ac.ncl.csc8404.payment.*;
import uk.ac.ncl.csc8404.user.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test
 * This class tests the business logic, including order processing
 * and report generation, of the {@link OrderSystemImpl} class.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
class OrderSystemTest
{

    private OrderSystem system;
    private Customer customerAlice, customerBob, customerCharlie;
    private CreditCard cardAlice, cardBob, cardCharlie;
    private PCModel gamingPC, officePC, budgetPC;
    private PCModel customPC;

    @BeforeEach
    void setUp()
    {
        system = new OrderSystemImpl();

        // Create customers
        customerAlice = new CustomerImpl("Alice", "Smith");
        customerBob = new CustomerImpl("Bob", "Jones");
        customerCharlie = new CustomerImpl("Charlie", "Brown");

        // Create valid credit cards
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        Date validDate = cal.getTime();
        cardAlice = CreditCardFactory.getInstance("11111111", validDate, "Alice Smith");
        cardBob = CreditCardFactory.getInstance("22222222", validDate, "Bob Jones");
        cardCharlie = CreditCardFactory.getInstance("33333333", validDate, "Charlie Brown");

        // Create preset models
        gamingPC = new PresetModel("GamerX", "BuildsRUs", Arrays.asList("i9-CPU", "4090-GPU"));
        officePC = new PresetModel("OfficePal", "Dell", Arrays.asList("i5-CPU", "Onboard-GPU"));
        budgetPC = new PresetModel("BudgetBox", "BuildsRUs", Arrays.asList("i3-CPU", "Onboard-GPU"));

        // Create a custom model
        customPC = CustomModelFactory.getInstance("Bob-Custom");
        ((CustomModel) customPC).addPart("i9-CPU");
        ((CustomModel) customPC).addPart("Extra-RAM");
    }

    @Test
    void testPlaceAndFulfillOrder()
    {
        // Normal case: Place an order and check history
        assertEquals(0, system.getOrderHistory().size());
        Order order = system.placeOrder(List.of(gamingPC), customerAlice, cardAlice);
        assertEquals(1, system.getOrderHistory().size());
        assertSame(order, system.getOrderHistory().get(0));

        // Normal case: Fulfill the order
        FulfillmentResult details = system.fulfillOrder(order);
        assertNotNull(details);

        Map<String, Map<String, Integer>> presetOrders = details.getPresetModelsFromManufacturers();
        assertEquals(1, presetOrders.get("BuildsRUs").get("GamerX"));
    }

    @Test
    void testOrderProcessingExceptions()
    {
        Order order = system.placeOrder(List.of(gamingPC), customerAlice, cardAlice);
        system.fulfillOrder(order);

        // Exceptional case: Fulfilling an already fulfilled order
        assertThrows(IllegalStateException.class, () -> system.fulfillOrder(order));

        // Exceptional case: Cancelling a fulfilled order
        assertThrows(IllegalStateException.class, () -> system.cancelOrder(order));
    }



    @Test
    void testGetLargestCustomer()
    {
        // Alice: 2 fulfilled orders
        system.fulfillOrder(system.placeOrder(List.of(gamingPC), customerAlice, cardAlice));
        system.fulfillOrder(system.placeOrder(List.of(officePC), customerAlice, cardAlice));

        // Bob: 1 fulfilled order
        system.fulfillOrder(system.placeOrder(List.of(budgetPC), customerBob, cardBob));

        // Charlie: 2 fulfilled orders (tie with Alice, but name comes later)
        system.fulfillOrder(system.placeOrder(List.of(gamingPC), customerCharlie, cardCharlie));
        system.fulfillOrder(system.placeOrder(List.of(customPC), customerCharlie, cardCharlie));

        Map.Entry<Customer, Integer> largest = system.getLargestCustomer();
        assertEquals(customerAlice, largest.getKey(), "Alice should be largest due to alphabetical tie-break.");
        assertEquals(2, largest.getValue());
    }

    @Test
    void testGetMostOrderedModel()
    {
        // GamingPC: ordered 2 times
        system.fulfillOrder(system.placeOrder(List.of(gamingPC), customerAlice, cardAlice));
        system.fulfillOrder(system.placeOrder(List.of(gamingPC), customerBob, cardBob));

        // OfficePal: ordered 2 times (tie with GamingPC, but name comes later)
        system.fulfillOrder(system.placeOrder(List.of(officePC), customerCharlie, cardCharlie));
        system.fulfillOrder(system.placeOrder(List.of(officePC), customerAlice, cardAlice));

        // BudgetBox: ordered 1 time
        system.fulfillOrder(system.placeOrder(List.of(budgetPC), customerBob, cardBob));

        Map.Entry<PCModel, Integer> mostOrdered = system.getMostOrderedModel();
        assertEquals(gamingPC, mostOrdered.getKey(), "GamerX should be most ordered due to alphabetical tie-break.");
        assertEquals(2, mostOrdered.getValue());
    }

    @Test
    void testGetMostOrderedPart()
    {
        PCModel custom1 = CustomModelFactory.getInstance("C1");
        ((CustomModel) custom1).addPart("PartA");
        ((CustomModel) custom1).addPart("PartB");
        ((CustomModel) custom1).addPart("PartC");

        PCModel custom2 = CustomModelFactory.getInstance("C2");
        ((CustomModel) custom2).addPart("PartB");
        ((CustomModel) custom2).addPart("PartC");

        PCModel custom3 = CustomModelFactory.getInstance("C3");
        ((CustomModel) custom3).addPart("PartC");

        // Counts: PartA=1, PartB=2, PartC=3
        system.fulfillOrder(system.placeOrder(List.of(custom1), customerAlice, cardAlice));
        system.fulfillOrder(system.placeOrder(List.of(custom2), customerBob, cardBob));
        system.fulfillOrder(system.placeOrder(List.of(custom3), customerCharlie, cardCharlie));

        Map.Entry<String, Integer> mostUsed = system.getMostOrderedPart();
        assertEquals("PartC", mostUsed.getKey());
        assertEquals(3, mostUsed.getValue());
    }

    @Test
    void testReportsWithNoFulfilledOrders()
    {
        // Place orders but do not fulfill them
        system.placeOrder(List.of(gamingPC), customerAlice, cardAlice);
        system.placeOrder(List.of(officePC), customerBob, cardBob);

        assertNull(system.getLargestCustomer(), "Returns null if no orders are fulfilled.");
        assertNull(system.getMostOrderedModel(), "Returns null if no orders are fulfilled.");
        assertNull(system.getMostOrderedPart(), "Returns null if no orders are fulfilled.");
    }
}