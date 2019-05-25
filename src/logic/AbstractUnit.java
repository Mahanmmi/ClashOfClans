package logic;

abstract class AbstractUnit {
    private Coordinate coordinate;
    private final int id;
    private final boolean isBlue;
    private int direction; // 1 for upward   -1 for downward
    private int hitpoint;
    private AbstractArmor armor;
    private AbstractWeapon weapon;

    public AbstractUnit(Coordinate coordinate, int id, boolean isBlue, int hitpoint, AbstractArmor armor, AbstractWeapon weapon) {
        this.coordinate = coordinate;
        this.id = id;
        this.isBlue = isBlue;
        this.hitpoint = hitpoint;
        this.armor = armor;
        this.weapon = weapon;

        if (isBlue) {
            direction = 1;
        } else {
            direction = -1;
        }
    }

    abstract void act();

    boolean isAlive() {
        if (hitpoint > 0) {
            return true;
        } else {
            hitpoint = 0;
            return false;
        }
    }

    boolean isBlue() {
        return isBlue;
    }
}
