import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerrainTest {

    Terrain plains = Terrain.PLAINS;

    @Test
    @DisplayName("Testing Hill value")
    public void checkHillValue(){
        Terrain hill = Terrain.HILL;
        assertEquals(4, hill.bonus);
    }

    @Test
    @DisplayName("Testing Forest value")
    public void checkForestValue(){
        Terrain forest = Terrain.FOREST;
        assertEquals(4, forest.bonus);
    }

    @Test
    @DisplayName("Testing Hill value")
    public void checkPlainsValue(){
        Terrain plains = Terrain.PLAINS;
        assertEquals(4, plains.bonus);
    }

    @Test
    @DisplayName("Testing Equals Method")
    public void checkEqualMethod(){
        Terrain hill = Terrain.HILL;
        assertEquals(Terrain.HILL, hill);
    }
}
