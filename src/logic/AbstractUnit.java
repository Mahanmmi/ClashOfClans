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

    AbstractUnit(String type, int hitpoint, Coordinate coordinate, int id, boolean isBlue, int attackDelay, AbstractArmor armor, AbstractWeapon weapon) {
        this.type = type;
        this.hitpoint = hitpoint;
        this.coordinate = coordinate;
        this.id = id;
        this.isBlue = isBlue;
        this.attackDelay = attackDelay;
        currentAttackDelay = this.attackDelay;
        this.armor = armor;
        this.weapon = weapon;
    }

    String getType() {
        return type;
    }

    int getId() {
        return id;
    }

    abstract ArrayList<Action> act();

    boolean isBlue() {
        return isBlue;
    }

    int getAttackDelay() {
        return attackDelay;
    }

    AbstractWeapon getWeapon() {
        return weapon;
    }

    void setCurrentAttackDelay(int currentAttackDelay) {
        this.currentAttackDelay = currentAttackDelay;
    }

    int getCurrentAttackDelay() {
        return currentAttackDelay;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
//        return "Id: " + getId() +
//                ", Type: " + getType() +
//                ", isBlue: " + isBlue() +
//                ", HP: " + hitpoint +
//                ", Coordinate: " + coordinate;
        return getId() + "," + getType() + "," + hitpoint + "," + getCoordinate().getX() + "," + getCoordinate().getY();
    }
}