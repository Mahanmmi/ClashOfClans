package logic;

import java.util.ArrayList;

public class Sling extends AbstractWeapon {
    public Sling() {
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
                        ||(j == 0 && i == 0)
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
