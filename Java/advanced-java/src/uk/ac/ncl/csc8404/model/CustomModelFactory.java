package uk.ac.ncl.csc8404.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *{@link CustomModel} creates new instances from factory.
 * Ensures custom PC model hava unique name. Follows
 *
 * factory pattern for created objects and prevents
 * creation of two separate {@code CustomModel} objects with the same name.
 *
 * @author Swapnil Sagar
 * @version 1.0
 *
 */

public final class CustomModelFactory
{

    private static final Map<String, PCModel> MODELS = new HashMap<>();

    /**
     * Private constructor to prevent class instantiation.
     */

    private CustomModelFactory()
    {}

    /**
     * Gets a unique instance of a custom model for the given name.
     * A new model is created, stored, and returned if now present earlier.
     * It returns {@link PCModel} interface type.
     * @param modelName It is unique name for custom model. Can't be empty.
     * @return A unique {@link PCModel} instance corresponding to the given name.
     * @throws IllegalArgumentException if modelName is empty.
     */
    public static PCModel getInstance(String modelName)
    {

        if (modelName == null || modelName.trim().isEmpty()) {
            throw new IllegalArgumentException("Custom model name cannot be null.");

        }

        String trimmedName = modelName.trim();

        // This is a thread-safe way to perform "put if absent"

        if (!MODELS.containsKey(trimmedName))
        {
            PCModel newModel = new CustomModel(trimmedName);
            MODELS.put(trimmedName, newModel);

        }

        return MODELS.get(trimmedName);
    }

    /**
     * This method @return the count of unique custom models
     *
     */

    public static int getNumberOfUniqueModels()
    {
        return MODELS.size();

    }
}