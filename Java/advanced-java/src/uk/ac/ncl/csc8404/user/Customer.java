package uk.ac.ncl.csc8404.user;

/**
 * An interface representing a customer.
 * It defines customer object for retrieving
 * their first name, last name, and full name.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public interface Customer
{

    /**
     * Returns the first name of the customer.
     *
     * @return the customer's first name.
     */

    String getFirstName();

    /**
     * Returns the last name of the customer.
     *
     * @return the customer's last name.
     */
    String getLastName();

    /**
     * Returns the full name of the customer, typically formatted as "FirstName LastName".
     *
     * @return the customer's full name.
     */
    String getFullName();
}
