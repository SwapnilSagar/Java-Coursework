package uk.ac.ncl.csc8404.payment;

import java.util.Date;

/**
 * An interface for credit card.
 *
 * Have credit card details such as the card number,
 * expiry date, and holder's name. It can also check the validity
 *
 * of the card.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public interface CreditCard
{

    /**
     * Generate and returns 8-digit card number (unique).
     *
     * @return the card number as a string.
     */

    String getCardNumber();

    /**
     * Returns card expiry date.
     *
     * @return a {@link Date} object as expiry date.
     */

    Date getExpiryDate();

    /**
     * Returns name.
     *
     * @return the holder's name.
     */
    String getHolderName();

    /**
     * Card validity check
     * A card is considered valid if it's not expired and all its
     * necessary details are present
     *
     * @return true if the card is valid else false.
     */

    boolean isValid();
}
