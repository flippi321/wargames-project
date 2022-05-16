import no.ntnu.idatt2001.Wargames.Battle.Terrain;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerrainTest {
    @Test
    @DisplayName("Testing Equals Method")
    public void checkEqualMethod(){
        Terrain hill = Terrain.HILL;
        assertEquals(Terrain.HILL, hill);
    }
}
