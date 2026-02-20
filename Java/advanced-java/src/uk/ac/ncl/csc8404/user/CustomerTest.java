package uk.ac.ncl.csc8404.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test
 * This class tests all public methods of the {@link CustomerImpl} class, ensuring
 *
 * its contract. Tests cover normal operation, boundary conditions,
 * and exceptional cases as recommended by good testing practices.
 *
 * @author Your Name
 * @version 1.0
 */
class CustomerTest
{



    @Test
    void testConstructorWithInvalidArguments()
    {
        // Test exceptional cases: null names
        assertThrows(IllegalArgumentException.class, () -> new CustomerImpl(null, "Doe"),
                "Constructor should throw exception for null first name.");

        assertThrows(IllegalArgumentException.class, () -> new CustomerImpl("John", null),
                "Constructor should throw exception for null last name.");

        assertThrows(IllegalArgumentException.class, () -> new CustomerImpl("", "Doe"),
                "Constructor should throw exception for empty first name.");
        assertThrows(IllegalArgumentException.class, () -> new CustomerImpl("  ", "Doe"),
                "Constructor should throw exception for blank first name.");
    }

    @Test
    void testEqualsAndHashCodeContract()
    {
        // Create customer objects for testing
        Customer john1 = new CustomerImpl("John", "Doe");
        Customer john2 = new CustomerImpl("John", "Doe");
        Customer jane = new CustomerImpl("Jane", "Doe");
        Customer differentJohn = new CustomerImpl("John", "Smith");

        // 1. Reflexivity: x.equals(x) should be true [cite: 363]
        assertEquals(john1, john1, "A customer must be equal to itself.");

        // 2. Symmetry: if x.equals(y) then y.equals(x) [cite: 365-366]
        assertEquals(john1, john2, "Two customers with the same name should be equal.");
        assertEquals(john2, john1, "Equality must be symmetric.");

        // 3. Test for inequality
        assertNotEquals(john1, jane, "Customers with different first names should not be equal.");
        assertNotEquals(john1, differentJohn, "Customers with different last names should not be equal.");

        // 4. Test against null: x.equals(null) should be false [cite: 372]
        assertNotEquals(null, john1, "A customer should not be equal to null.");

        // 5. Test against different type
        assertNotEquals("John Doe", john1, "A customer should not be equal to an object of a different type.");

        // 6. hashCode contract: if x.equals(y), then x.hashCode() == y.hashCode() [cite: 438-439]
        assertEquals(john1.hashCode(), john2.hashCode(), "Equal customers must have the same hash code.");

    }



    @Test
    void testValueOf()
    {
        // Test normal case for the static valueOf factory method
        String customerString = "Alice Wonderland";
        Customer alice = CustomerImpl.valueOf(customerString);

        assertEquals("Alice", alice.getFirstName());
        assertEquals("Wonderland", alice.getLastName());
        assertEquals(customerString, alice.toString());

        // Test exceptional cases for valueOf
        assertThrows(IllegalArgumentException.class, () -> CustomerImpl.valueOf("InvalidName"),
                "valueOf should throw exception for incorrect format.");

        assertThrows(IllegalArgumentException.class, () -> CustomerImpl.valueOf(null),
                "valueOf should throw exception for null input.");
    }

}