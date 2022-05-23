package no.ntnu.idatt2001.Wargames.Battle;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Equals method to make sure that I don't have to Override it to find to equal Enum types
 */
public class TerrainTest {
    @Test
    @DisplayName("Testing Equals Method")
    public void checkEqualMethod(){
        Terrain hill = Terrain.HILL;
        assertEquals(Terrain.HILL, hill);
    }

    @Test
    @DisplayName("Testing Equals Method with different Weathers")
    public void checkFalseEqualMethod(){
        Terrain hill = Terrain.HILL;
        assertEquals(Terrain.HILL, hill);
    }
}
