package minastirith.gameobject.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrossbowmanTest {

    @Test
    void doRangedDamage() {
        Crossbowman crossbowman = new Crossbowman();

        assertEquals(10,crossbowman.doRangedDamage());
    }
}