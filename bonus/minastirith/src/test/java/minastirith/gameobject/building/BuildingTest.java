package minastirith.gameobject.building;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    @Test
    void testTower() {
        Building tower = new Tower();

        assertTrue(tower.sufferDamage(99));
        assertEquals(1, tower.hitPoints);

        assertEquals(0, tower.getLoot());

        assertFalse(tower.sufferDamage(10000));
    }

    @Test
    void testWall() {
        Building wall = new Wall();

        assertTrue(wall.sufferDamage(100));
        assertEquals(50, wall.hitPoints);

        assertEquals(0, wall.getLoot());

        assertFalse(wall.sufferDamage(10000));
    }

}