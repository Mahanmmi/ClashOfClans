package logic;

import java.util.ArrayList;

public class Sword extends AbstractWeapon {
    public Sword() {
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
