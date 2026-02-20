package uk.ac.ncl.csc8404.order;

import uk.ac.ncl.csc8404.model.PCModel;
import uk.ac.ncl.csc8404.payment.CreditCard;
import uk.ac.ncl.csc8404.user.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the {@link Order} interface.
 * Single order information is stored here. It begins with
 * a 'PLACED' -> 'FULFILLED' or 'CANCELLED'.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public final class OrderImpl implements Order
{

    private final List<PCModel> models;
    private final Customer customer;
    private final CreditCard creditCard;
    private final Date orderDate;
    private OrderStatus status;

    /**
     * New Order Constructor. Date and time are automatically assigned to order
     *
     * and an initial status of 'PLACED'.
     *
     * @param models list of PC models.
     * @param customer customer.
     *
     * @param creditCard credit card.
     * @throws IllegalArgumentException if any parameter is null.
     */

    public OrderImpl(List<PCModel> models, Customer customer, CreditCard creditCard)
    {

        if (models == null || models.isEmpty())
        {
            throw new IllegalArgumentException("Model list cannot be null or empty.");
        }

        if (customer == null)
        {
            throw new IllegalArgumentException("Customer cannot be null.");
        }

        if (creditCard == null)
        {
            throw new IllegalArgumentException("Credit card cannot be null.");

        }

        this.models = new ArrayList<>(models); // Defensive copy
        this.customer = customer;
        this.creditCard = creditCard;
        this.orderDate = new Date();
        this.status = OrderStatus.PLACED;
    }

    @Override
    public List<PCModel> getModels()
    {

        return Collections.unmodifiableList(models);
    }

    @Override
    public Customer getCustomer()
    {

        return customer;
    }

    @Override
    public CreditCard getCreditCard()
    {

        return creditCard;
    }

    @Override
    public Date getOrderDate()
    {

        return new Date(orderDate.getTime()); // Defensive copy

    }


    @Override
    public OrderStatus getStatus()
    {

        return status;
    }

    @Override
    public void fulfill()
    {
        if (this.status == OrderStatus.CANCELLED)
        {
            throw new IllegalStateException("Cancelled order can't be fulfilled.");
        }

        this.status = OrderStatus.FULFILLED;
    }

    @Override
    public void cancel()
    {

        if (this.status == OrderStatus.FULFILLED) {
            throw new IllegalStateException("Fulfilled order can't be cancelled.");
        }
        this.status = OrderStatus.CANCELLED;
    }

    @Override
    public String toString()
    {

        return "Order Customer=" + customer.getFullName() + ", Date=" + orderDate + ", Status=" + status;
    }

}
