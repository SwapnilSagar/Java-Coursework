package uk.ac.ncl.csc8404.user;

import java.util.Objects;

/**
 * A concrete, immutable implementation of the {@link Customer} interface.
 * <p>
 * This class represents a customer with a first and last name. It follows the rule
 * that two customers are considered equal if they share the same first and last names.
 * It provides a static factory method {@code valueOf} to create instances from a string.
 *
 * @author Your Name
 * @version 1.0
 */
public final class CustomerImpl implements Customer
{

    private final String firstName;
    private final String lastName;


    /**
     * Constructs a new Customer.
     *
     * @param firstName the first name of the customer.
     * @param lastName the last name of the customer.
     *
     * @throws IllegalArgumentException if either name is null.
     */
    public CustomerImpl(String firstName, String lastName)
    {

        // Defensive check
        if (firstName == null || firstName.trim().isEmpty())
        {
            throw new IllegalArgumentException("First name cannot be null.");
        }

        if (lastName == null || lastName.trim().isEmpty())
        {
            throw new IllegalArgumentException("Last name cannot be null.");
        }
        this.firstName = firstName.trim();

        this.lastName = lastName.trim();
    }

    @Override
    public String getFirstName()
    {
        return firstName;
    }


    @Override
    public String getLastName()
    {

        return lastName;
    }

    @Override
    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    /**
     * Compares with another object for equality.
     * Two customers are equal if their first and last names are the same.
     *
     *
     * @param o the object to compare against.
     *
     * @return true if the given object is a Customer with the same names, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {

        if (this == o) return true;

        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;

        return Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getLastName(), customer.getLastName());
    }

    /**
     * Returns a hash code for this customer.
     * The hash code is computed based on the first and last names.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode()
    {
        int result = 17;

        result = 31 * result + Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);

        return result;
    }

    /**
     * Returns a string representation of the customer.
     *
     * @return the customer's full name.
     */
    @Override
    public String toString()
    {
        return getFullName();

    }

    /**
     * Creates a Customer instance from string representation.
     * This static factory method complements the {@code toString} method.
     *
     * @param customerString a string in the format "FirstName LastName".
     * @return a new {@link Customer} instance.
     *
     * @throws IllegalArgumentException if the string format is invalid.
     */
    public static Customer valueOf(String customerString)
    {

        if (customerString == null || customerString.trim().isEmpty())
        {
            throw new IllegalArgumentException("Input string for valueOf cannot be null or empty.");
        }

        String[] parts = customerString.trim().split("\\s+", 2);
        if (parts.length != 2)
        {
            throw new IllegalArgumentException("Invalid format for customer string. Expected 'FirstName LastName'.");
        }

        return new CustomerImpl(parts[0], parts[1]);
    }


}