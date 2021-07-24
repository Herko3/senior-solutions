package minastirith.army;

import minastirith.gameobject.unit.MeleeUnit;
import minastirith.gameobject.unit.RangedUnit;

import java.util.ArrayList;
import java.util.List;

public abstract class Army {

    protected String name;
    protected final List<MeleeUnit> meleeUnits;
    protected final List<RangedUnit> rangedUnits;

    protected Army(String name) {
        this.name = name;
        this.meleeUnits = new ArrayList<>();
        this.rangedUnits = new ArrayList<>();
    }

    public abstract void addBasicMeleeUnit(int unitCount);

    public abstract void addEliteMeleeUnit(int unitCount);

    public abstract void addRangedUnit(int unitCount);

    public abstract String getStatistics();

    public List<MeleeUnit> getMeleeUnits() {
        return meleeUnits;
    }

    public List<RangedUnit> getRangedUnits() {
        return rangedUnits;
    }
}
