package logic;

import java.util.ArrayList;

abstract class AbstractUnit {
    int hitpoint;
    final Coordinate coordinate;
    private final int id;
    private final boolean isBlue;
    private final String type;
    int direction; // 1 for upward   -1 for downward
    int attackDelay;
    int currentAttackDelay = 0;
    AbstractArmor armor;
    AbstractWeapon weapon;

    public AbstractUnit(String type, int hitpoint, Coordinate coordinate, int id, boolean isBlue, int attackDelay, AbstractArmor armor, AbstractWeapon weapon) {
        this.type = type;
        this.hitpoint = hitpoint;
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

    String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getHitpoint() {
        return hitpoint;
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

    public int getCurrentAttackDelay() {
        return currentAttackDelay;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Id: "+ getId() +
                ", Type: " + getType() +
                ", isBlue: " + isBlue() +
                ", HP: " + hitpoint +
                ", Coordinate: "  + coordinate;
    }
}