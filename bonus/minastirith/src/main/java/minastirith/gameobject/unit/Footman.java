package minastirith.gameobject.unit;

import minastirith.config.ConstantValues;

public class Footman extends Unit implements MeleeUnit {

    public Footman() {
        super(ConstantValues.FOOTMAN_HP, ConstantValues.FOOTMAN_DAMAGE, ConstantValues.FOOTMAN_LOOT);
    }

    @Override
    public double doMeleeDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Footman: [" + health + "]";
    }

}
