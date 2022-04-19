import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerrainTest {

    Terrain plains = Terrain.PLAINS;

    @Test
    @DisplayName("Testing Hill value")
    public void checkHillValue(){
        Terrain hill = Terrain.HILL;
        assertEquals(2, hill.bonus);
    }

    @Test
    @DisplayName("Testing Forest value")
    public void checkForestValue(){
        Terrain forest = Terrain.FOREST;
        assertEquals(2, forest.bonus);
    }

    @Test
    @DisplayName("Testing Hill value")
    public void checkPlainsValue(){
        Terrain plains = Terrain.PLAINS;
        assertEquals(2, plains.bonus);
    }
}
