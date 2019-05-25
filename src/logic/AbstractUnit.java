package logic;

import java.util.ArrayList;

abstract class AbstractUnit {
    final Coordinate coordinate;
    final int id;
    final boolean isBlue;
    int direction; // 1 for upward   -1 for downward
    int attackDelay;
    int currentAttackDelay = 0;
    AbstractArmor armor;
    AbstractWeapon weapon;

    public AbstractUnit(Coordinate coordinate, int id, boolean isBlue, int attackDelay, AbstractArmor armor, AbstractWeapon weapon) {
        this.coordinate = coordinate;
        this.id = id;
        this.isBlue = isBlue;
        this.attackDelay = attackDelay;
        this.armor = armor;
        this.weapon = weapon;

        if (isBlue) {
            direction = 1;
        } else {
            direction = -1;
        }
    }

    abstract ArrayList<Action> act();

    boolean isBlue() {
        return isBlue;
    }

    public int getAttackDelay() {
        return attackDelay;
    }

    public AbstractWeapon getWeapon() {
        return weapon;
    }

    public void setCurrentAttackDelay(int currentAttackDelay) {
        this.currentAttackDelay = currentAttackDelay;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
