package minastirith.gameobject.unit;

import minastirith.config.ConstantValues;

public class Troll extends Unit implements MeleeUnit {

    public Troll() {
        super(ConstantValues.URUK_HAI_HP, ConstantValues.URUK_HAI_DAMAGE, ConstantValues.URUK_HAI_LOOT);
    }

    @Override
    public double doMeleeDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Troll: [" + health + "]";
    }

}
