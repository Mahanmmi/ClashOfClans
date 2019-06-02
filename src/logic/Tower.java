package logic;

import java.util.ArrayList;

import static logic.Main.chart;

class Tower extends AbstractUnit {
    Tower(Coordinate coordinate, int id, boolean isBlue) {
        super("Tower", 10000, coordinate, id, isBlue, 10, new StoneArmor(), new Sling());
    }

    private ArrayList<Action> attack(ChartCell[][] chart) {
        ArrayList<Action> attackActions = new ArrayList<>();
        if (currentAttackDelay == 0) {
            ArrayList<Coordinate> targetsCoordinates = weapon.canHit(chart, isBlue(), coordinate, 0);
            for (Coordinate targetCoordinate : targetsCoordinates) {
                AbstractSoldier target = (AbstractSoldier) chart[targetCoordinate.getX()][targetCoordinate.getY()].getUnit();
                if (target != null) {
                    System.out.print(this + " Now can attack: ");
                    System.out.println(target);
                    attackActions.add(new AttackAction(this, target, this.getCoordinate()));
                }
            }
            currentAttackDelay = getAttackDelay();
        }
        return attackActions;
    }

    @Override
    ArrayList<Action> act() {
        if (getCurrentAttackDelay() != 0) {
            setCurrentAttackDelay(getCurrentAttackDelay() - 1);
        }
        return (attack(chart));
    }


    static class Sling extends AbstractWeapon {
        private Sling() {
            super(2500, 10);
        }

        @Override
        ArrayList<Coordinate> getActionableCells(ChartCell[][] chart, Coordinate current, int direction) {
            ArrayList<Coordinate> actionableCells = new ArrayList<>();
            int x = current.getX();
            int y = current.getY();
            int n = chart.length;
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if ((x + i < 0)
                            || (x + i >= n)
                            || (j == 0 && i == 0)
                            || (y + j < 0)
                            || (y + j >= n)) {
                        continue;
                    }
                    actionableCells.add(new Coordinate(x + i, y + j));
                }
            }
            return actionableCells;
        }
    }

}
