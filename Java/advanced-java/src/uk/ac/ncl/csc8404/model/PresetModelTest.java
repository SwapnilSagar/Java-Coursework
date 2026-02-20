package uk.ac.ncl.csc8404.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class.
 * @author Swapnil Sagar
 * @version 1.0
 */
class PresetModelTest
{


    @Test
    void testConstructorWithInvalidArguments()
    {

        // Test exceptional cases for constructor
        List<String> validParts = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new PresetModel(null, "Dell", validParts));
        assertThrows(IllegalArgumentException.class, () -> new PresetModel(" ", "Dell", validParts));

        assertThrows(IllegalArgumentException.class, () -> new PresetModel("ModelX", null, validParts));
        assertThrows(IllegalArgumentException.class, () -> new PresetModel("ModelX", " ", validParts));
        assertThrows(IllegalArgumentException.class, () -> new PresetModel("ModelX", "Dell", null));

    }

    @Test
    void testImmutabilityOfPartsList()
    {
        List<String> originalParts = new ArrayList<>();
        originalParts.add("CPU");
        originalParts.add("Motherboard");
        PresetModel model = new PresetModel("Workstation", "HP", originalParts);

        // Scenario 1: Modify the original list after model creation
        originalParts.add("GPU");
        assertEquals(2, model.getParts().size(),
                "The model's internal list can't change when the external list is modified.");


        // Scenario 2: Modifying the list returned by getParts()
        List<String> retrievedParts = model.getParts();
        assertThrows(UnsupportedOperationException.class, () -> retrievedParts.add("Another GPU"),
                "The list returned by getParts() can't be modified.");

    }
}
