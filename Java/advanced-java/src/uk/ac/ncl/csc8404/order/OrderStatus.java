package uk.ac.ncl.csc8404.order;

/**
 * Enum to represent order status
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public enum OrderStatus
{
    /**
     * The order is placed.
     */
    PLACED,

    /**
     * The order has been completed.
     */
    FULFILLED,

    /**
     * The order has been cancelled and will not be processed.
     */
    CANCELLED
}
