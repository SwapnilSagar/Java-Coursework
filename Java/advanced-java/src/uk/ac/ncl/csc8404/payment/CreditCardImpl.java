package uk.ac.ncl.csc8404.payment;

import java.util.Date;

/**
 * A concrete, immutable implementation of the {@link CreditCard} interface.
 * <p>
 * This class stores the details of a credit card. Its constructor is package-private
 * to enforce instance control through the {@link CreditCardFactory}.
 *
 * @author Your Name
 * @version 1.0
 */
final class CreditCardImpl implements CreditCard
{


    private final String cardNumber;
    private final Date expiryDate;
    private final String holderName;

    /**
     * Constructs a new CreditCard instance.
     * Intentionally package-private to be controlled by the factory.
     *
     * @param cardNumber the 8-digit card number.
     * @param expiryDate the expiry date.
     *
     * @param holderName the name of the holder.
     */

    CreditCardImpl(String cardNumber, Date expiryDate, String holderName)
    {

        this.cardNumber = cardNumber;
        this.expiryDate = new Date(expiryDate.getTime());
        this.holderName = holderName;
    }

    @Override
    public String getCardNumber()
    {

        return cardNumber;
    }

    @Override
    public Date getExpiryDate()
    {

        return new Date(expiryDate.getTime()); // Defensive copy for immutability
    }

    @Override
    public String getHolderName()
    {

        return holderName;
    }

    @Override
    public boolean isValid()
    {

        // A card is valid if details are set and it has not expired[cite: 1327].
        if (cardNumber == null || expiryDate == null || holderName == null)
        {

            return false;
        }
        // The card is valid if its expiry date is not before the current date.
        return !expiryDate.before(new Date());

    }

    /**
     * Returns a string representation of the credit card for logging/debugging.
     *
     * Note: In a real system, you would never log the full card number.
     *
     * @return a string representation of the card.
     */
    @Override
    public String toString()
    {
        return "CreditCard Number=" + cardNumber + ", Expiry=" + expiryDate + ", Holder=" + holderName;

    }

}
