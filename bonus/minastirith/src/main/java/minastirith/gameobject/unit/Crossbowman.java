package minastirith.gameobject.unit;

import minastirith.config.ConstantValues;

public class Crossbowman extends Unit implements RangedUnit {
    public Crossbowman() {
        super(ConstantValues.CROSSBOWMAN_HP, ConstantValues.CROSSBOWMAN_DAMAGE, ConstantValues.CROSSBOWMAN_LOOT);
    }

    @Override
    public double doRangedDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Crossbowman: [" + health + "]";
    }
}
