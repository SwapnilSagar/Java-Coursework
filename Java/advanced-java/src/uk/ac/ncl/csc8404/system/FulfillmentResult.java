package uk.ac.ncl.csc8404.system;

import java.util.Map;
import java.util.Objects;

/**
 * An immutable value object representing the result of fulfilling an order.
 * This class return the complex data generated
 * by the fulfillment process.
 *
 * @author Swapnil Sagar
 * @version 1.0
 */
public final class FulfillmentResult
{

    private final Map<String, Map<String, Integer>> presetModelsFromManufacturers;
    private final Map<String, Integer> partsFromWarehouse;


    /**
     * Constructs a new FulfillmentResult.
     *
     * @param presetModelsFromManufacturers A map detailing preset models.
     * @param partsFromWarehouse A map detailing custom parts.
     */

    public FulfillmentResult(Map<String, Map<String, Integer>> presetModelsFromManufacturers, Map<String, Integer> partsFromWarehouse)
    {

        this.presetModelsFromManufacturers = presetModelsFromManufacturers;
        this.partsFromWarehouse = partsFromWarehouse;

    }

    public Map<String, Map<String, Integer>> getPresetModelsFromManufacturers()
    {
        return presetModelsFromManufacturers;
    }


    public Map<String, Integer> getPartsFromWarehouse()
    {
        return partsFromWarehouse;
    }

    @Override
    public boolean equals(Object obj)
    {

        if (this == obj) return true;
        if (!(obj instanceof FulfillmentResult)) return false;
        FulfillmentResult that = (FulfillmentResult) obj;
        return Objects.equals(presetModelsFromManufacturers, that.presetModelsFromManufacturers) &&
                Objects.equals(partsFromWarehouse, that.partsFromWarehouse);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(presetModelsFromManufacturers, partsFromWarehouse);
    }

    @Override
    public String toString()
    {
        return "FulfillmentResult{" +
                "presetModelsFromManufacturers=" + presetModelsFromManufacturers +
                ", partsFromWarehouse=" + partsFromWarehouse +
                '}';
    }
}
