package minastirith.gameobject.building;

import minastirith.config.ConstantValues;

public class
Tower extends Building {

    public Tower() {
        super(ConstantValues.TOWER_HP);
    }

    @Override
    public String toString() {
        return "Tower: [" + hitPoints + "]";
    }
}
