package minastirith.gameobject.building;

import minastirith.config.ConstantValues;

public class Wall extends Building {

    public Wall() {
        super(ConstantValues.WALL_HP);
    }

    @Override
    public String toString() {
        return "Wall: [" + hitPoints + "]";
    }
}
