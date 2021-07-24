package minastirith.army;

import minastirith.gameobject.building.Building;
import minastirith.gameobject.building.Tower;
import minastirith.gameobject.building.Wall;
import minastirith.gameobject.siege.Ballista;
import minastirith.gameobject.siege.Catapult;
import minastirith.gameobject.siege.SiegeWeapon;
import minastirith.gameobject.unit.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Test
    void addBasicMeleeUnit() {
        Army army = new Attacker();
        army.addBasicMeleeUnit(20);

        List<MeleeUnit> units = army.getMeleeUnits();

        assertThat(units)
                .hasSize(20);

        assertEquals(15,units.get(0).doMeleeDamage());
        assertTrue(units.get(9) instanceof Grunt);

    }

    @Test
    void addEliteMeleeUnit() {
        Army army = new Defender();
        army.addEliteMeleeUnit(15);

        List<MeleeUnit> units = army.getMeleeUnits();

        assertThat(units)
                .hasSize(15);

        assertEquals(20,units.get(0).doMeleeDamage());
        assertTrue(units.get(6) instanceof Knight);
    }

    @Test
    void addRangedUnit() {

        Army army = new Attacker();
        army.addRangedUnit(5);

        List<RangedUnit> units = army.getRangedUnits();
        assertThat(units)
                .hasSize(5);

        assertEquals(8,units.get(0).doRangedDamage());
        assertTrue(units.get(4) instanceof Archer);
    }

    @Test
    void attackerSiegeWeapons(){
        Attacker attacker = new Attacker();

        attacker.addBallista(5);
        attacker.addCatapult(5);

        List<SiegeWeapon> result = attacker.getSiegeWeapons();

        assertThat(result)
                .hasSize(10)
                .extracting(SiegeWeapon::doDamage)
                .contains(10.0,50.0);

        assertTrue(result.get(0) instanceof Ballista);
        assertTrue(result.get(5) instanceof Catapult);
    }

    @Test
    void defenderBuildings(){
        Defender defender = new Defender();

        defender.addTower(2);
        defender.addWall(5);

        List<Building> result = defender.getBuildings();

        assertThat(result)
                .hasSize(7)
                .extracting(Building::getLoot)
                .contains(0.0,0.0);

        assertTrue(result.get(0) instanceof Tower);
        assertTrue(result.get(6) instanceof Wall);
    }
}