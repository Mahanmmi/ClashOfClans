package logic;

import java.util.ArrayList;

abstract class AbstractWeapon {
    private final int damage;
    private final int refreshTime;

    public AbstractWeapon(int damage, int refreshTime) {
        this.damage = damage;
        this.refreshTime = refreshTime;
    }

    abstract ArrayList<Coordinate> actionableCells(ChartCell[][] chart);
    boolean canHit(ChartCell[][] chart, boolean isBlue){
        ArrayList<Coordinate> actionableCells = actionableCells(chart);
        for (Coordinate actionableCell : actionableCells) {
            int x = actionableCell.getX();
            int y = actionableCell.getY();
            if(chart[x][y].getUnit().isBlue() != isBlue){
                return true;
            }
        }
        return false;
    }
}
