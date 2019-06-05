package logic;

import java.util.ArrayList;

import static logic.Main.chart;

abstract class AbstractSoldier extends AbstractUnit {
    private int movementDelay;
    private int currentMovementDelay = 0;

    AbstractSoldier(String type, Coordinate coordinate, int id, boolean isBlue, int attackDelay, AbstractArmor armor, AbstractWeapon weapon,
                    int hitpoint, int movementDelay) {
        super(type, hitpoint, coordinate, id, isBlue, attackDelay, armor, weapon);
        if (isBlue)
            direction = 1;
        else
            direction = -1;

        this.movementDelay = movementDelay + armor.getWeight();
        this.attackDelay += weapon.getRefreshTime();
//        System.out.println(getType() + "," + movementDelay);
//        System.out.println(getType() + "," + attackDelay);
        setCurrentAttackDelay(this.attackDelay);
        setCurrentMovementDelay(this.movementDelay);
    }

    private MovementAction move(Coordinate target) {
        if (isAlive() && currentMovementDelay == 0) {
            return new MovementAction(this, target);
        }
        return null;
    }

    AttackAction attack(ChartCell[][] chart) {
        if (isAlive() && currentAttackDelay == 0) {
            ArrayList<Coordinate> targetCoordinates = weapon.canHit(chart, isBlue(), this.coordinate, direction);
            if (targetCoordinates.size() != 0) {
                Coordinate targetCoordinate = targetCoordinates.get(0);
                if (targetCoordinate != null) {
                    AbstractUnit target = chart[targetCoordinate.getX()][targetCoordinate.getY()].getUnit();
                    if (this instanceof SwordMan) {
                        return new AttackAction(this, target, target.getCoordinate());
                    } else if (this instanceof SpearMan) {
                        Coordinate coordinate;
                        if (this.getCoordinate().getX() + direction == chart.length || this.getCoordinate().getX() + direction == -1) {
                            direction = -direction;
                        }
                        coordinate = new Coordinate(this.getCoordinate().getX() + direction, this.getCoordinate().getY());
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
        if (currentMovementDelay != 0) {
            currentMovementDelay--;
        }
        if (getCurrentAttackDelay() != 0) {
            setCurrentAttackDelay(getCurrentAttackDelay() - 1);
        }
        if (weapon.canHit(chart, isBlue(), coordinate, direction).size() == 0) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            if (x + direction == chart.length || x + direction == -1) {
                direction = -direction;
            }
//            System.out.println("Excuse me WTF1");
            if (chart[x + direction][y].getUnit() == null) {
                arr.add(move(new Coordinate(x + direction, y)));
                return arr;
            }
//            System.out.println("Excuse me WTF2");
            for (int i = 1; i >= -1; i -= 2) {
                if ((y + i < 0) || (y + i) >= chart[x].length || chart[x + direction][y + i].getUnit() != null) {
                    continue;
                }
                arr.add(move(new Coordinate(x, y + i)));
                return arr;
            }
//            System.out.println("Excuse me WTF3");
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

    int getMovementDelay() {
        return movementDelay;
    }

    void setCurrentMovementDelay(int currentMovementDelay) {
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
