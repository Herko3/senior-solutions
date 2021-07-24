package minastirith.gameobject.unit;

import minastirith.config.ConstantValues;

public class Grunt extends Unit implements MeleeUnit {

    public Grunt() {
        super(ConstantValues.GRUNT_HP, ConstantValues.GRUNT_DAMAGE, ConstantValues.GRUNT_LOOT);
    }

    @Override
    public double doMeleeDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Grunt: [" + health + "]";
    }
}
