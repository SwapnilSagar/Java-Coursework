package uk.ac.ncl.csc8404.model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class.
 * Tests the factory's ability to enforce unique model names and verifies
 * the mutable behavior of the CustomModel class itself.
 *
 * @author Your Name
 * @version 1.0
 */
class CustomModelFactoryTest
{


    @Test
    void testFactoryForUniqueness()
    {
        // Test that the factory returns the same instance for the same name
        PCModel model1 = CustomModelFactory.getInstance("MyUniqueBuild");
        PCModel model2 = CustomModelFactory.getInstance("MyUniqueBuild");

        assertSame(model1, model2, "Identical object to be returned for the same name.");
    }


    @Test
    void testAddAndRemoveParts()
    {

        // Test the mutable behavior of CustomModel
        PCModel pcModel = CustomModelFactory.getInstance("BuildInProgress");

        assertTrue(pcModel instanceof CustomModel, "It should create an instance of CustomModel.");
        CustomModel customModel = (CustomModel) pcModel;

        assertEquals(0, customModel.getParts().size(), "No parts for newly added model.");

        // Add parts
        customModel.addPart("CPU:-i7");
        customModel.addPart("RAM:-16GB");
        assertEquals(2, customModel.getParts().size());
        assertTrue(customModel.getParts().contains("CPU:-i7"));

        // Remove a part
        customModel.removePart("CPU:-i7");
        assertEquals(1, customModel.getParts().size());
        assertFalse(customModel.getParts().contains("CPU:-i7"));
    }




}

