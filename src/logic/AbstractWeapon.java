package logic;

import java.util.ArrayList;
import java.util.Comparator;

abstract class AbstractWeapon {
    private final int damage;
    private final int refreshTime;

    public AbstractWeapon(int damage, int refreshTime) {
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
            if (chart[x][y].getUnit().isBlue() != isBlue) {
                targets.add(actionableCell);
            }
        }
        if (targets.size() == 0) {
            return null;
        }
        targets.sort(Comparator.comparingInt(Coordinate::getX).thenComparingInt(Coordinate::getY));
        return targets;
    }

    public int getDamage() {
        return damage;
    }

    public int getRefreshTime() {
        return refreshTime;
    }
}
