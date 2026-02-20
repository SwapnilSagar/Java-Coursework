package uk.ac.ncl.csc8404.order;

import uk.ac.ncl.csc8404.model.PCModel;
import uk.ac.ncl.csc8404.payment.CreditCard;
import uk.ac.ncl.csc8404.user.Customer;

import java.util.Date;
import java.util.List;

/**
 * An interface representing customer's order.
 * Provides all information related to order,
 * showing ordered models, customer details, and the order's current status.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public interface Order
{


    /**
     * @return an unmodifiable list of {@link PCModel} objects.
     */
    List<PCModel> getModels();

    /**
     * @return the {@link Customer} object.
     */

    Customer getCustomer();

    /**
     * @return the {@link CreditCard} object.
     */

    CreditCard getCreditCard();

    /**
     * @return the order {@link Date} date.
     */

    Date getOrderDate();

    /**
     * @return the {@link OrderStatus} current order status.
     */

    OrderStatus getStatus();

    /**
     * Update order status to FULFILLED.
     */

    void fulfill();

    /**
     * Update order status to CANCELLED.
     */

    void cancel();
}