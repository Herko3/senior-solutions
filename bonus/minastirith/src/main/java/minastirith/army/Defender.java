package minastirith.army;

import minastirith.config.ConstantValues;
import minastirith.gameobject.building.Building;
import minastirith.gameobject.building.Tower;
import minastirith.gameobject.building.Wall;
import minastirith.gameobject.unit.Crossbowman;
import minastirith.gameobject.unit.Footman;
import minastirith.gameobject.unit.Knight;

import java.util.ArrayList;
import java.util.List;

public class Defender extends Army {

    public final List<Building> buildings;

    public Defender() {
        super(ConstantValues.DEFENDER_NAME);
        this.buildings = new ArrayList<>();
    }

    @Override
    public void addBasicMeleeUnit(int unitCount) {
        for (int i = 0; i < unitCount; i++) {
            meleeUnits.add(new Footman());
        }

    }

    @Override
    public void addEliteMeleeUnit(int unitCount) {
        for (int i = 0; i < unitCount; i++) {
            meleeUnits.add(new Knight());
        }
    }

    @Override
    public void addRangedUnit(int unitCount) {
        for (int i = 0; i < unitCount; i++) {
            rangedUnits.add(new Crossbowman());
        }
    }

    public void addWall(int count) {
        for (int i = 0; i < count; i++) {
            buildings.add(new Wall());
        }
    }

    public void addTower(int count) {
        for (int i = 0; i < count; i++) {
            buildings.add(new Tower());
        }
    }

    @Override
    public String getStatistics() {
        String stats = name + ":\n";
        stats += "    Number of melee units: " + meleeUnits.size() + "\n";
        stats += "    Number of ranged units: " + rangedUnits.size() + "\n";
        stats += "    Number of buildings: " + buildings.size() + "\n";
        return stats;
    }

    public List<Building> getBuildings() {
        return buildings;
    }
}
