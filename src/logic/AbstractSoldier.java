package logic;

import java.util.ArrayList;

import static logic.Main.chart;

abstract class AbstractSoldier extends AbstractUnit {
    private int movementDelay;
    private int currentMovementDelay = 0;

    public AbstractSoldier(String type,Coordinate coordinate, int id, boolean isBlue, int attackDelay, AbstractArmor armor, AbstractWeapon weapon,
                           int hitpoint, int movementDelay) {
        super(type,hitpoint,coordinate, id, isBlue, attackDelay, armor, weapon);
        this.movementDelay = movementDelay + armor.getWeight();
        this.attackDelay += weapon.getRefreshTime();
    }

    MovementAction move(Coordinate target) {
        if (isAlive() && currentMovementDelay == 0) {
            return new MovementAction(this, target);
        }
        return null;
    }

    AttackAction attack(ChartCell[][] chart) {
        if (isAlive() && currentAttackDelay == 0) {

            ArrayList<Coordinate> targetCoordinates = weapon.canHit(chart, isBlue(), this.coordinate, direction);
            if(targetCoordinates.size()!=0) {
                Coordinate targetCoordinate = targetCoordinates.get(0);
                if (targetCoordinate != null) {
                    AbstractSoldier target = (AbstractSoldier) chart[targetCoordinate.getX()][targetCoordinate.getY()].getUnit();
                    if (this instanceof SwordMan) {
                        return new AttackAction(this, target, target.getCoordinate());
                    } else if (this instanceof SpearMan) {
                        Coordinate coordinate;
                        if (this.getCoordinate().getY() + direction == chart.length || this.getCoordinate().getY() + direction == -1) {
                            direction = -direction;
                        }
                        coordinate = new Coordinate(this.getCoordinate().getX(), this.getCoordinate().getY() + direction);
                        return new AttackAction(this, target, coordinate);
                    }
                }
            }
        }
        return null;
    }

    @Override
    ArrayList<Action> act() {
        ArrayList<Action> arr = new ArrayList<>();
        if (weapon.canHit(chart, isBlue(), coordinate, direction) == null) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            if (y + direction == chart.length || y + direction == -1) {
                direction = -direction;
            }
            if (chart[x][y + direction].getUnit() == null) {
                arr.add(move(new Coordinate(x, y + direction)));
                return arr;
            }

            for (int i = -1; i <= 1; i += 2) {
                if ((x + i < 0) || (x + i) >= chart.length || chart[x + i][y + direction].getUnit() != null) {
                    continue;
                }
                arr.add(move(new Coordinate(x + i, y)));
                return arr;
            }
            arr.add(move(new Coordinate(x, y)));
            return arr;
        }
        arr.add(attack(chart));
        return arr;
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

    @Override
    public String toString() {
        return getId() +
                ", " + hitpoint +
                ", " + coordinate;
    }
}
