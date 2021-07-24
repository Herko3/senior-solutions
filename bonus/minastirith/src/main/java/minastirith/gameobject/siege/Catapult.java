package minastirith.gameobject.siege;

import minastirith.config.ConstantValues;

public class Catapult extends SiegeWeapon {

    public Catapult() {
        super(ConstantValues.CATAPULT_DAMAGE);
    }

    @Override
    public String toString() {
        return "Catapult";
    }

}
