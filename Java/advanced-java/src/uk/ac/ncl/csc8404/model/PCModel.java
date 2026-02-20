package uk.ac.ncl.csc8404.model;

import java.util.List;

/**
 * An interface for PC model.
 * Shows common contract for PC models,
 *
 * both preset or custom-built. Ensures every
 * model has name and list of computer parts.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public interface PCModel
{

    /**
     * Method to get and return,
     * @return the model name.
     *
     */
    String getName();

    /**
     * Method to get the list of computer parts in this model.
     *
     * Returned list can be modified.
     *
     * @return list of parts as strings.
     */
    List<String> getParts();

}
