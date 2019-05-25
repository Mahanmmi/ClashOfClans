package logic;

abstract class AbstractSoldier extends AbstractUnit {
    int hitpoint;
    int movementDelay;
    int currentMovementDelay = 0;

    public AbstractSoldier(Coordinate coordinate, int id, boolean isBlue, int attackDelay, AbstractArmor armor, AbstractWeapon weapon,
                           int hitpoint, int movementDelay) {
        super(coordinate, id, isBlue, attackDelay, armor, weapon);
        this.hitpoint = hitpoint;
        this.movementDelay = movementDelay;
    }

    void move(Coordinate target) {
        if (isAlive() && currentMovementDelay == 0) {
            coordinate.moveTo(target);
            currentMovementDelay = movementDelay;
        }
    }

    void attack(ChartCell[][] chart) {
        if (isAlive() && currentAttackDelay == 0) {
            Coordinate targetCoordinate = weapon.canHit(chart, isBlue, direction).get(0);
            if (targetCoordinate != null) {
                AbstractSoldier target = (AbstractSoldier) chart[targetCoordinate.getX()][targetCoordinate.getY()].getUnit();
            }
        }
    }

    void takeDamage(int damage) {
        hitpoint -= armor.takeDamage(damage);
        isAlive();
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getMovementDelay() {
        return movementDelay;
    }

    public void setCurrentMovementDelay(int currentMovementDelay) {
        this.currentMovementDelay = currentMovementDelay;
    }

    boolean isAlive() {
        if (hitpoint > 0) {
            return true;
        } else {
            hitpoint = 0;
            return false;
        }
    }
}
