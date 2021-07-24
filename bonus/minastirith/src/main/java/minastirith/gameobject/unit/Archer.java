package minastirith.gameobject.unit;

import minastirith.config.ConstantValues;

public class Archer extends Unit implements RangedUnit {

    public Archer() {
        super(ConstantValues.ARCHER_HP, ConstantValues.ARCHER_DAMAGE, ConstantValues.ARCHER_LOOT);
    }

    @Override
    public double doRangedDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Archer: [" + health + "]";
    }
}
