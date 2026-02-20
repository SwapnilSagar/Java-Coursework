package uk.ac.ncl.csc8404.payment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Creating unique {@link CreditCard} instances for factory.
 *
 * Only one CreditCard object should exist in the system for a given card number.
 * This is an example of an instance-controlled class.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */

public final class CreditCardFactory
{


    private static final Map<String, CreditCard> CARDS = new HashMap<>();

    // Private constructor to prevent instantiation of the factory itself.

    private CreditCardFactory()
    {}

    /**
     * Returns a unique CreditCard instance.
     *
     * @param cardNumber 8-digit card number. Must be in a valid format.
     * @param expiryDate the card's expiry date.
     *
     * @param holderName the name of the card holder.
     * @return a unique {@link CreditCard} instance.
     * @throws IllegalArgumentException if any parameters are invalid.
     */
    public static CreditCard getInstance(String cardNumber, Date expiryDate, String holderName)
    {
        // Defensive parameter checks
        if (cardNumber == null || !cardNumber.matches("\\d{8}"))
        {
            throw new IllegalArgumentException("Card number must be an 8-digit string.");
        }

        if (expiryDate == null)
        {
            throw new IllegalArgumentException("Expiry date cannot be null.");

        }
        if (holderName == null || holderName.trim().isEmpty())
        {
            throw new IllegalArgumentException("Holder name cannot be null or empty.");
        }

        if (!CARDS.containsKey(cardNumber))
        {
            CreditCard newCard = new CreditCardImpl(cardNumber, expiryDate, holderName);
            CARDS.put(cardNumber, newCard);

        }

        return CARDS.get(cardNumber);
    }


    /**
     * @return the count of unique cards.
     */

    public static int getNumberOfUniqueCards() {
        return CARDS.size();
    }
}