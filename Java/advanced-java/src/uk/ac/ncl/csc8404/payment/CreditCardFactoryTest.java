package uk.ac.ncl.csc8404.payment;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardFactoryTest {

    private Date getFutureDate()
    {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.YEAR, 2);
        return cal.getTime();
    }

    @Test
    void testGetInstanceForUniqueness()
    {

        // Test normal case: ensure the factory returns the same instance for the same card number
        CreditCard cardA = CreditCardFactory.getInstance("12345678", getFutureDate(), "Alice");

        // Requesting the same card number again should return the *exact same* object
        CreditCard cardB = CreditCardFactory.getInstance("12345678", getFutureDate(), "Alice B");

        // Use assertSame to check for object identity, not just equality
        assertSame(cardA, cardB, "Factory should return the existing instance for a known card number.");
        assertEquals("Alice", cardA.getHolderName(), "The details of the original card should not be updated.");
    }

    @Test
    void testGetInstanceWithInvalidParameters()
    {
        // Test exceptional cases: invalid constructor arguments
        assertThrows(IllegalArgumentException.class, () -> CreditCardFactory.getInstance(null, getFutureDate(), "Holder"),
                "Should throw exception for null card number.");

        assertThrows(IllegalArgumentException.class, () -> CreditCardFactory.getInstance("12345", getFutureDate(), "Holder"),
                "Should throw exception for card number with incorrect length.");

        assertThrows(IllegalArgumentException.class, () -> CreditCardFactory.getInstance("abcdefgh", getFutureDate(), "Holder"),
                "Should throw exception for non-digit card number.");

        assertThrows(IllegalArgumentException.class, () -> CreditCardFactory.getInstance("87654321", null, "Holder"),
                "Should throw exception for null expiry date.");

        assertThrows(IllegalArgumentException.class, () -> CreditCardFactory.getInstance("87654321", getFutureDate(), null),
                "Should throw exception for null holder name.");

    }

    @Test
    void testCardValidity()
    {

        CreditCard validCard = CreditCardFactory.getInstance("99998888", getFutureDate(), "Valid Holder");
        assertTrue(validCard.isValid());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date expiredDate = cal.getTime();

        CreditCard expiredCard = new CreditCardImpl("11223344", expiredDate, "Expired Holder");
        assertFalse(expiredCard.isValid());

    }


}
