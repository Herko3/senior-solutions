package minastirith.gameobject.building;

import minastirith.gameobject.Damageable;

public abstract class Building implements Damageable {
    protected double hitPoints;

    protected Building(double hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public boolean sufferDamage(double damage) {
        hitPoints -= damage;
        return hitPoints > 0;
    }

    @Override
    public double getLoot() {
        return 0;
    }
}
