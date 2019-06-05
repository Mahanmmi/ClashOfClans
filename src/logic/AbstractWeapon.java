package logic;

import java.util.ArrayList;

abstract class AbstractWeapon {
    private final int damage;
    private final int refreshTime;

    AbstractWeapon(int damage, int refreshTime) {
        this.damage = damage;
        this.refreshTime = refreshTime;
    }

    abstract ArrayList<Coordinate> getActionableCells(ChartCell[][] chart, Coordinate current, int direction);

    ArrayList<Coordinate> canHit(ChartCell[][] chart, boolean isBlue, Coordinate current, int direction) {
        ArrayList<Coordinate> actionableCells = getActionableCells(chart, current, direction);
        ArrayList<Coordinate> targets = new ArrayList<>();
        for (Coordinate actionableCell : actionableCells) {
            int x = actionableCell.getX();
            int y = actionableCell.getY();
            if (chart[x][y].getUnit() != null && chart[x][y].getUnit() instanceof AbstractSoldier && chart[x][y].getUnit().isBlue() != isBlue) {
                targets.add(actionableCell);
            }
        }
        targets.sort((Coordinate a, Coordinate b) -> {
            AbstractUnit firstUnit = chart[a.getX()][a.getY()].getUnit();
            AbstractUnit secondUnit = chart[b.getX()][b.getY()].getUnit();
            if (firstUnit != null && secondUnit != null) {
                return Integer.compare(firstUnit.getId(), secondUnit.getId());
            }
            return 0;
        });
        return targets;
    }

    int getDamage() {
        return damage;
    }

    int getRefreshTime() {
        return refreshTime;
    }
}
