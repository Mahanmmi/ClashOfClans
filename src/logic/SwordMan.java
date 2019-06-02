package logic;

import java.util.ArrayList;

public class SwordMan extends AbstractSoldier {
    public SwordMan(Coordinate coordinate, int id, boolean isBlue) {
        super("SwordMan",coordinate, id, isBlue, 0, new ChainArmor(), new Sword(), 5000, 2);
    }

    @Override
    AttackAction attack(ChartCell[][] chart) {
        AttackAction action = super.attack(chart);
        attackDelay++;
        return action;
    }

    private static class Sword extends AbstractWeapon {
        private Sword() {
            super(2000, 1);
        }

        @Override
        ArrayList<Coordinate> getActionableCells(ChartCell[][] chart, Coordinate current, int direction) {
            ArrayList<Coordinate> actionableCells = new ArrayList<>();
            int x = current.getX();
            int y = current.getY();
            int n = chart.length;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((j == 0 && i == 0)
                            || (x + i < 0)
                            || (x + i >= n)
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
