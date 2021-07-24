package minastirith.gameobject.unit;

import minastirith.config.ConstantValues;

public class Knight extends Unit implements MeleeUnit {

    public Knight() {
        super(ConstantValues.KNIGHT_HP, ConstantValues.KNIGHT_DAMAGE, ConstantValues.KNIGHT_LOOT);
    }

    @Override
    public double doMeleeDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Knight: [" + health + "]";
    }
}
