package uk.ac.ncl.csc8404.system;

import uk.ac.ncl.csc8404.model.*;
import uk.ac.ncl.csc8404.order.*;
import uk.ac.ncl.csc8404.payment.*;
import uk.ac.ncl.csc8404.user.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of the {@link OrderSystem} interface.
 * It manages the entire order history and provides the business logic
 * for processing and analyzing orders.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public final class OrderSystemImpl implements OrderSystem
{

    private final List<Order> orderHistory = new ArrayList<>();


    @Override
    public Order placeOrder(List<PCModel> models, Customer customer, CreditCard creditCard)
    {
        if (!creditCard.isValid())
        {
            throw new IllegalArgumentException("Cannot place order with an invalid credit card.");
        }
        Order order = new OrderImpl(models, customer, creditCard);
        orderHistory.add(order);
        return order;
    }


    @Override
    public void cancelOrder(Order order)
    {
        if (order.getStatus() == OrderStatus.FULFILLED)
        {
            throw new IllegalStateException("Cannot cancel an order that has already been fulfilled.");
        }
        order.cancel();
    }


    @Override
    public FulfillmentResult fulfillOrder(Order order)
    {
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.FULFILLED)
        {
            throw new IllegalStateException("Order cannot be fulfilled as it is " + order.getStatus());
        }
        order.fulfill();

        Map<String, Map<String, Integer>> presetOrders = new HashMap<>();
        Map<String, Integer> customParts = new HashMap<>();

        for (PCModel model : order.getModels())
        {
            if (model instanceof PresetModel)
            {
                PresetModel preset = (PresetModel) model;
                presetOrders.computeIfAbsent(preset.getManufacturer(), k -> new HashMap<>())
                        .merge(preset.getName(), 1, Integer::sum);
            }
            else if (model instanceof CustomModel)
            {
                for (String part : model.getParts())
                {
                    customParts.merge(part, 1, Integer::sum);
                }
            }
        }

        return new FulfillmentResult(presetOrders, customParts);
    }


    private List<Order> getFulfilledOrders()
    {

        List<Order> fulfilledOrders = new ArrayList<>();
        for (Order order : orderHistory)
        {
            if (order.getStatus() == OrderStatus.FULFILLED)
            {
                fulfilledOrders.add(order);
            }
        }
        return fulfilledOrders;

    }

    @Override
    public Map.Entry<Customer, Integer> getLargestCustomer()
    {

        List<Order> fulfilledOrders = getFulfilledOrders();
        if (fulfilledOrders.isEmpty())
        {
            return null;
        }

        Map<Customer, Integer> customerCounts = new HashMap<>();
        for (Order order : fulfilledOrders)
        {
            customerCounts.merge(order.getCustomer(), 1, Integer::sum);
        }

        Customer largestCustomer = null;
        int maxOrders = -1;

        for (Map.Entry<Customer, Integer> entry : customerCounts.entrySet())
        {
            if (entry.getValue() > maxOrders)
            {
                maxOrders = entry.getValue();
                largestCustomer = entry.getKey();
            }
            else if (entry.getValue() == maxOrders)
            {
                // Alphabetical sort rule
                if (entry.getKey().getFullName().compareTo(largestCustomer.getFullName()) < 0)
                {
                    largestCustomer = entry.getKey();
                }
            }

        }
        return new AbstractMap.SimpleEntry<>(largestCustomer, maxOrders);
    }


    @Override
    public Map.Entry<PCModel, Integer> getMostOrderedModel()
    {
        List<Order> fulfilledOrders = getFulfilledOrders();
        if (fulfilledOrders.isEmpty())
        {
            return null;
        }

        Map<PCModel, Integer> modelCounts = new HashMap<>();
        for (Order order : fulfilledOrders)
        {
            for (PCModel model : order.getModels())
            {
                if (model instanceof PresetModel)
                {
                    modelCounts.merge(model, 1, Integer::sum);
                }
            }

        }

        if (modelCounts.isEmpty())
        {
            return null;
        }


        PCModel mostOrderedModel = null;
        int maxCount = -10;

        for (Map.Entry<PCModel, Integer> entry : modelCounts.entrySet())
        {
            if (entry.getValue() > maxCount)
            {

                maxCount = entry.getValue();
                mostOrderedModel = entry.getKey();
            }

            else if (entry.getValue() == maxCount)
            {
                // Alphabetical sort rule
                if (entry.getKey().getName().compareTo(mostOrderedModel.getName()) < 0)
                {
                    mostOrderedModel = entry.getKey();
                }
            }
        }

        return new AbstractMap.SimpleEntry<>(mostOrderedModel, maxCount);
    }

    @Override
    public Map.Entry<String, Integer> getMostOrderedPart()
    {
        List<Order> fulfilledOrders = getFulfilledOrders();
        if (fulfilledOrders.isEmpty())
        {
            return null;
        }


        Map<String, Integer> partCounts = new HashMap<>();
        for (Order order : fulfilledOrders)
        {

            for (PCModel model : order.getModels())
            {
                if (model instanceof CustomModel)
                {
                    for (String part : model.getParts())
                    {
                        partCounts.merge(part, 1, Integer::sum);
                    }
                }
            }

        }

        if (partCounts.isEmpty())
        {
            return null;
        }

        String mostOrderedPart = null;
        int maxCount = -1;

        for (Map.Entry<String, Integer> entry : partCounts.entrySet())
        {
            if (entry.getValue() > maxCount)
            {
                maxCount = entry.getValue();
                mostOrderedPart = entry.getKey();
            }

            else if (entry.getValue() == maxCount)
            {
                // Alphabetical sort rule
                if (entry.getKey().compareTo(mostOrderedPart) < 0)
                {
                    mostOrderedPart = entry.getKey();
                }

            }
        }
        return new AbstractMap.SimpleEntry<>(mostOrderedPart, maxCount);
    }


    @Override
    public List<Order> getOrderHistory()
    {
        return Collections.unmodifiableList(orderHistory);
    }
}