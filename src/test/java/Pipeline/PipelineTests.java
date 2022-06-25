package Pipeline;

import PipeLine.PipeLine;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class PipelineTests {

    @Test
    public void T_22_1_pipeline_is_created_successfully(){
        // Arrange
        PipeLine pipeLine = new PipeLine("New Pipeline", false);

        // Act
        String name = pipeLine.getPipeLineName();

        // Assert
        assertEquals(name, "New Pipeline");
    }

    @Test
    public void T_22_2_automatic_pipeline_is_created_successfully(){
        // Arrange
        PipeLine pipeLine = new PipeLine("New Pipeline", true);

        // Act
        boolean automatic = pipeLine.isAutomatic();

        // Assert
        assertTrue(automatic);
    }

    @Test
    public void T_22_3_manual_pipeline_is_created_successfully(){
        // Arrange
        PipeLine pipeLine = new PipeLine("New Pipeline", false);

        // Act
        boolean automatic = pipeLine.isAutomatic();

        // Assert
        assertFalse(automatic);
    }

}
