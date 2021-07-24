package minastirith.gameobject.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrollTest {

    Troll troll;

    @BeforeEach
    void init(){
        troll = new Troll();
    }

    @Test
    void testDMG(){
        assertEquals(18,troll.doMeleeDamage());
    }

    @Test
    void sufferDMG(){
        troll.sufferDamage(20);
        assertEquals(180,troll.health);
    }

}