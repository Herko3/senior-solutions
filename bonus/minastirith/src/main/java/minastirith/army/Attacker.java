package minastirith.army;

import minastirith.config.ConstantValues;
import minastirith.gameobject.siege.Ballista;
import minastirith.gameobject.siege.Catapult;
import minastirith.gameobject.siege.SiegeWeapon;
import minastirith.gameobject.unit.Archer;
import minastirith.gameobject.unit.Grunt;
import minastirith.gameobject.unit.Troll;

import java.util.ArrayList;
import java.util.List;

public class Attacker extends Army {

    private final List<SiegeWeapon> siegeWeapons;

    public Attacker() {
        super(ConstantValues.ATTACKER_NAME);
        this.siegeWeapons = new ArrayList<>();
    }

    @Override
    public void addBasicMeleeUnit(int unitCount) {
        for (int i = 0; i < unitCount; i++) {
            meleeUnits.add(new Grunt());
        }
    }

    @Override
    public void addEliteMeleeUnit(int unitCount) {
        for (int i = 0; i < unitCount; i++) {
            meleeUnits.add(new Troll());
        }
    }

    @Override
    public void addRangedUnit(int unitCount) {
        for (int i = 0; i < unitCount; i++) {
            rangedUnits.add(new Archer());
        }
    }

    public void addCatapult(int count) {
        for (int i = 0; i < count; i++) {
            siegeWeapons.add(new Catapult());
        }
    }

    public void addBallista(int count) {
        for (int i = 0; i < count; i++) {
            siegeWeapons.add(new Ballista());
        }
    }

    @Override
    public String getStatistics() {
        String stats = name + ":\n";
        stats += "    Number of melee units: " + meleeUnits.size() + "\n";
        stats += "    Number of ranged units: " + rangedUnits.size() + "\n";
        stats += "    Number of siege weapons: " + siegeWeapons.size() + "\n";
        return stats;
    }

    public List<SiegeWeapon> getSiegeWeapons() {
        return siegeWeapons;
    }
}
