package uk.ac.ncl.csc8404.system;

import uk.ac.ncl.csc8404.model.PCModel;
import uk.ac.ncl.csc8404.order.Order;
import uk.ac.ncl.csc8404.payment.CreditCard;
import uk.ac.ncl.csc8404.user.Customer;

import java.util.List;
import java.util.Map;

/**
 * PC retailer's order management system interface.
 * This contract defines the placing, cancelling, and fulfilling orders
 * as well as generating reports on sales data.
 * @author Swapnil Sagar
 * @version 1.0
 */
public interface OrderSystem
{

    /**
     * Places order for customer.
     *
     * @param models     The ordered list of PC models ordered.
     * @param customer   The order placed customer.
     * @param creditCard The credit card used for payment.
     * @return The newly created {@link Order}.
     * @throws IllegalArgumentException if the credit card is invalid.
     */
    Order placeOrder(List<PCModel> models, Customer customer, CreditCard creditCard);

    /**
     * Cancels an existing order.
     *
     * @param order The order to be cancelled.
     * @throws IllegalStateException if the order has already been fulfilled.
     */
    void cancelOrder(Order order);

    /**
     * Fulfills an existing order.
     *
     * @param order The order to be fulfilled.
     * @return A map for fulfillment: a list of preset models
     * to order and collect list of part from warehouse.
     * @throws IllegalStateException if the order has been cancelled or already fulfilled.
     */
    FulfillmentResult fulfillOrder(Order order);

    /**
     * Customer with the highest number of fulfilled orders.
     *
     * @return A map entry containing customer with highest fulfilled order count.
     */
    Map.Entry<Customer, Integer> getLargestCustomer();

    /**
     * Gets the most ordered preset model across all fulfilled orders.
     *
     * @return A map entry containing the most popular preset model with order count.
     */
    Map.Entry<PCModel, Integer> getMostOrderedModel();

    /**
     * Gets the most ordered part in custom models across all fulfilled orders.
     *
     * @return A map entry containing the most popular part with order count.
     */
    Map.Entry<String, Integer> getMostOrderedPart();

    /**
     * Returns a view of the entire order history.
     * @return An unmodifiable list of all orders placed.
     */
    List<Order> getOrderHistory();

}