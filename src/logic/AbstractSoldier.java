package logic;

import static logic.Main.chart;

abstract class AbstractSoldier extends AbstractUnit {
    int hitpoint;
    int movementDelay;
    int currentMovementDelay = 0;

    public AbstractSoldier(Coordinate coordinate, int id, boolean isBlue, int attackDelay, AbstractArmor armor, AbstractWeapon weapon,
                           int hitpoint, int movementDelay) {
        super(coordinate, id, isBlue, attackDelay, armor, weapon);
        this.hitpoint = hitpoint;
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
            Coordinate targetCoordinate = weapon.canHit(chart, isBlue, this.coordinate, direction).get(0);
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
        return null;
    }

    @Override
    Action act() {
        if (weapon.canHit(chart, isBlue, coordinate, direction) == null) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            if (y + direction == chart.length || y + direction == -1) {
                direction = -direction;
            }
            if (chart[x][y + direction].getUnit() == null) {
                return move(new Coordinate(x, y + direction));
            }

            for (int i = -1; i <= 1; i += 2) {
                if ((x + i < 0) || (x + i) >= chart.length || chart[x + i][y + direction].getUnit() != null) {
                    continue;
                }
                return move(new Coordinate(x + i, y));
            }
            return move(new Coordinate(x, y));
        }
        return attack(chart);
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
