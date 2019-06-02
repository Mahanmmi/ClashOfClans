package logic;

import java.util.ArrayList;

public class SpearMan extends AbstractSoldier {
    public SpearMan(Coordinate coordinate, int id, boolean isBlue) {
        super("SpearMan", coordinate, id, isBlue, 0, new ChainArmor(), new Spear(), 3000, 1);
    }

    private static class Spear extends AbstractWeapon {
        private Spear() {
            super(1500, 2);
        }

        @Override
        ArrayList<Coordinate> getActionableCells(ChartCell[][] chart, Coordinate current, int direction) {
            ArrayList<Coordinate> actionableCells = new ArrayList<>();
            int x = current.getX();
            int y = current.getY();
            int n = chart.length;
            for (int i = -1; i <= 1; i++) {
                for (int j = 1; j <= 2; j++) {
                    if ((x + i < 0)
                            || (x + i >= n)
                            || (y + direction * j < 0)
                            || (y + direction * j >= n)) {
                        continue;
                    }
                    actionableCells.add(new Coordinate(x + i, y + direction * j));
                }
            }
            return actionableCells;
        }
    }
}
