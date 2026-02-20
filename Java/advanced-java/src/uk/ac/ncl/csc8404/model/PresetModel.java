package uk.ac.ncl.csc8404.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An immutable implementation of {@link PCModel} for preset PCs.
 *
 * This represents PC with fixed parts by a specific
 * manufacturer. Defensive copies used to ensure immutability.
 *
 * @author Swapnil Sagar
 * @version 1.0
 *
 */
public final class PresetModel extends AbstractPCModel
{


    private final String manufacturer;
    private final List<String> parts;

    /**
     * PresetModel Constructor.
     *
     * @param modelName model name.
     * @param manufacturer Manufacturer name (3rd party).
     *
     * @param parts fixed list parts of model.
     * @throws IllegalArgumentException if any parameter is null.
     */


    public PresetModel(String modelName, String manufacturer, List<String> parts)
    {
        super(modelName); // Calls the abstract class constructor


        if (manufacturer == null || manufacturer.trim().isEmpty())
        {
            throw new IllegalArgumentException("Manufacturer cannot be null.");
        }
        if (parts == null)
        {
            throw new IllegalArgumentException("Parts list cannot be null.");
        }

        this.manufacturer = manufacturer;


        this.parts = new ArrayList<>(parts);
    }

    @Override
    public String getName()
    {
        return super.getName();
    }

    /**
     * Gets name of the manufacturer.
     *
     * @return manufacturer's name.
     */
    public String getManufacturer()
    {
        return manufacturer;

    }

    @Override
    public List<String> getParts()
    {
        return Collections.unmodifiableList(parts);

    }

    @Override
    public String toString()
    {

        return "PresetModel Name=" + getName() + ", Manufacturer=" + manufacturer;
    }

    // Overriding equals and hashCode.
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (!(o instanceof PresetModel)) return false;

        PresetModel that = (PresetModel) o;
        return getName().equals(that.getName()) &&
                manufacturer.equals(that.manufacturer);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getName(), manufacturer);

    }
}
