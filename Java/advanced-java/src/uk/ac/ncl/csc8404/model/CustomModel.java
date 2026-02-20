package uk.ac.ncl.csc8404.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @link PCModel for custom-built PCs.
 * In this class customer can configure PC by adding or
 *
 * removing parts. With {@code CustomModelFactory} its constructor is created.
 * @author Swapnil Sagar
 * @version 1.0
 */
public final class CustomModel extends AbstractPCModel
{

    private final List<String> parts;


    /**
     * CustomModel constructor.
     * This constructor uses factory for
     *
     * creating instances, for unique names.
     * @param modelName Unique name for custom model.
     */

    CustomModel(String modelName)
    {
        super(modelName); // Call to the abstract class constructor
        this.parts = new ArrayList<>();
    }


    @Override
    public String getName()
    {

        return super.getName(); // Delegated to the abstract class
    }

    @Override
    public List<String> getParts()
    {

        // Return a copy to prevent modification in internal list,
        // use of addPart/removePart methods.
        return new ArrayList<>(parts);
    }

    /**
     * Adds part to custom model.
     * @param part part to add, can't be empty.
     *
     */

    public void addPart(String part)
    {
        if (part != null && !part.trim().isEmpty())
        {
            this.parts.add(part);

        }
    }

    /**
     * Removes part from custom model.\
     * @param part part to remove.
     *
     */

    public void removePart(String part)
    {

        if (part != null)
        {
            this.parts.remove(part);

        }
    }

    @Override
    public String toString()
    {
        return "CustomModel Name=" + getName() + ", Parts=" + parts.size();
    }

}
