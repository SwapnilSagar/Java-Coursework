package uk.ac.ncl.csc8404.model;

/**
 * Abstract base class for PCModel implementation.
 * This class gives functionality like storage and retrieval
 * of the model name, to reduce duplication in subclasses.
 * Demonstrates hierarchy pattern as discussed in the lectures.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public abstract class AbstractPCModel implements PCModel
{

    private final String modelName;

    /**
     * Constructs the base of a PC model.
     *
     * @param modelName the name of the model.
     */
    protected AbstractPCModel(String modelName)
    {

        if (modelName == null || modelName.trim().isEmpty())
        {
            throw new IllegalArgumentException("Model name cannot be null.");
        }

        this.modelName = modelName;
    }

    @Override
    public String getName()
    {
        return modelName;
    }

}
