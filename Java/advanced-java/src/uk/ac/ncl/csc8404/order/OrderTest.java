package uk.ac.ncl.csc8404.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.csc8404.model.*;
import uk.ac.ncl.csc8404.payment.*;
import uk.ac.ncl.csc8404.user.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
class OrderTest
{

    private Customer dummyCustomer;
    private CreditCard dummyCard;
    private List<PCModel> dummyModels;

    @BeforeEach
    void setUp()
    {
        // Use @BeforeEach to set up common test data to avoid repetition
        dummyCustomer = new CustomerImpl("Test", "User");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        dummyCard = CreditCardFactory.getInstance("88887777", cal.getTime(), "Test User");

        dummyModels = List.of(new PresetModel("TestPC", "TestCorp", Arrays.asList("CPU", "RAM")));
    }



    @Test
    void testConstructorWithInvalidArguments()
    {
        // Test exceptional cases for the constructor
        assertThrows(IllegalArgumentException.class, () -> new OrderImpl(null, dummyCustomer, dummyCard),
                "Constructor give exception for null model list.");

        assertThrows(IllegalArgumentException.class, () -> new OrderImpl(new ArrayList<>(), dummyCustomer, dummyCard),
                "Constructor give exception for empty model list.");

        assertThrows(IllegalArgumentException.class, () -> new OrderImpl(dummyModels, null, dummyCard),
                "Constructor give exception for null customer.");

        assertThrows(IllegalArgumentException.class, () -> new OrderImpl(dummyModels, dummyCustomer, null),
                "Constructor give exception for null credit card.");
    }

    @Test
    void testOrderStatusTransitions()
    {
        // Test state management methods
        Order order = new OrderImpl(dummyModels, dummyCustomer, dummyCard);
        assertEquals(OrderStatus.PLACED, order.getStatus());

        // Transition to FULFILLED
        order.fulfill();
        assertEquals(OrderStatus.FULFILLED, order.getStatus(), "Order status should change to FULFILLED.");

        // Reset and transition to CANCELLED
        order = new OrderImpl(dummyModels, dummyCustomer, dummyCard);
        order.cancel();
        assertEquals(OrderStatus.CANCELLED, order.getStatus(), "Order status should change to CANCELLED.");

    }

    @Test
    void testIllegalStateTransitions()
    {
        // Test that a fulfilled order cannot be cancelled
        Order order1 = new OrderImpl(dummyModels, dummyCustomer, dummyCard);
        order1.fulfill();
        assertThrows(IllegalStateException.class, order1::cancel, "Should not be able to cancel a fulfilled order.");

        // Test that a cancelled order cannot be fulfilled
        Order order2 = new OrderImpl(dummyModels, dummyCustomer, dummyCard);
        order2.cancel();
        assertThrows(IllegalStateException.class, order2::fulfill, "Should not be able to fulfill a cancelled order.");

    }

}