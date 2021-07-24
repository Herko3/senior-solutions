package minastirith.gameobject.unit;

import minastirith.gameobject.Damageable;

public abstract class Unit implements Damageable {
    protected double health;
    protected double damage;
    protected double loot;

    protected Unit(double health, double damage, double loot) {
        this.health = health;
        this.damage = damage;
        this.loot = loot;
    }

    @Override
    public boolean sufferDamage(double damage) {
        this.health -= damage;
        return this.health > 0;
    }

    @Override
    public double getLoot() {
        return this.loot;
    }
}
