package minastirith.gameobject.siege;

import minastirith.config.ConstantValues;

public class Ballista extends SiegeWeapon {
    public Ballista() {
        super(ConstantValues.BALLISTA_DAMAGE);
    }

    @Override
    public String toString() {
        return "Ballista";
    }
}
