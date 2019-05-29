package logic;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    static ChartCell[][] chart;

    private static void tick() {
        ArrayList<Action> turnActions = new ArrayList<>();

        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[i].length; j++) {
                ChartCell cell = chart[i][j];
                if (cell.getUnit() != null) {
                    turnActions.addAll(cell.getUnit().act());
                }
            }
        }

        for (Action action : turnActions) {
            if (action != null) {
                action.doAction();
            }
        }
    }

    private static void printChart() {
        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[i].length; j++) {
                AbstractUnit unit = chart[i][j].getUnit();
                if (unit == null) {
                    System.out.print("- ");
                    continue;
                }
                switch (unit.getType()) {
                    case "Tower": {
                        if (unit.isBlue()) {
                            System.out.print("T");
                        } else {
                            System.out.print("t");
                        }
                        break;
                    }
                    case "SwordMan": {
                        if (unit.isBlue()) {
                            System.out.print("S");
                        } else {
                            System.out.print("s");
                        }
                        break;
                    }
                    case "SpearMan": {
                        if (unit.isBlue()) {
                            System.out.print("R");
                        } else {
                            System.out.print("r");
                        }
                        break;
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void initChartUnits(int count, boolean isBlue) {
        for (int i = 0; i < count; i++) {
            String[] unitData = scanner.next().split(",");
            int id = Integer.parseInt(unitData[0]);
            String type = unitData[1];
            Coordinate coordinate = new Coordinate(Integer.parseInt(unitData[2]), Integer.parseInt(unitData[3]));
            switch (type) {
                case "Tower": {
                    chart[coordinate.getY()][coordinate.getX()].setUnit(new Tower(coordinate, id, isBlue));
                    break;
                }
                case "SwordMan": {
                    chart[coordinate.getY()][coordinate.getX()].setUnit(new SwordMan(coordinate, id, isBlue));
                    break;
                }
                case "SpearMan": {
                    chart[coordinate.getY()][coordinate.getX()].setUnit(new SpearMan(coordinate, id, isBlue));
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        chart = new ChartCell[n][n];
        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[i].length; j++) {
                chart[i][j] = new ChartCell(new Coordinate(i, j));
            }
        }
        int blueCount = scanner.nextInt();
        int redCount = scanner.nextInt();
        initChartUnits(blueCount, true);
        initChartUnits(redCount, false);
        while (true){
            scanner.next();
            tick();
            printChart();
        }
    }
}
