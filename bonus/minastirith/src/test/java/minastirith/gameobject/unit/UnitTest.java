package minastirith.gameobject.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

   @Test
    void testTroll(){
       Unit troll = new Troll();

       troll.sufferDamage(20);

       assertEquals(180,troll.health);
       assertEquals(18,troll.damage);
       assertEquals(50,troll.getLoot());
   }

   @Test
   void testArcher(){
      Unit archer = new Archer();

      archer.sufferDamage(20);

      assertEquals(80,archer.health);
      assertEquals(8,archer.damage);
      assertEquals(15,archer.getLoot());
   }
}