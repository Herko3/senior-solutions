package minastirith.gameobject.siege;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SiegeWeaponTest {

    @Test
    void testBallista(){
        SiegeWeapon ballista = new Ballista();

        assertEquals(50,ballista.doDamage());
    }

    @Test
    void testCatapult(){
        SiegeWeapon catapult = new Catapult();

        assertEquals(10,catapult.doDamage());
    }

}