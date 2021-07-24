package minastirith.gameobject.siege;

public abstract class SiegeWeapon {
    protected double damage;

    protected SiegeWeapon(double damage) {
        this.damage = damage;
    }

    public double doDamage() {
        return damage;
    }
}
