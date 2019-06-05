package logic;

import java.io.FileNotFoundException;
import java.io.PrintStream;
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
//                    System.out.println("!!!");
                    turnActions.addAll(cell.getUnit().act());
                }
            }
        }
        turnActions.sort((Action a, Action b) -> {
            if(a instanceof AttackAction && b instanceof AttackAction){
                return Integer.compare(((AttackAction) b).getUnit().getId(),((AttackAction) a).getUnit().getId());
            }
            return 0;
        });
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

    private static void initChartUnits(int count, boolean isBlue, ArrayList<AbstractUnit> units) {
        for (int i = 0; i < count; i++) {
            String[] unitData = scanner.next().split(",");
            int id = Integer.parseInt(unitData[0]);
            String type = unitData[1];
            Coordinate coordinate = new Coordinate(Integer.parseInt(unitData[2]), Integer.parseInt(unitData[3]));
            AbstractUnit unit = null;
            switch (type) {
                case "Tower": {
                    unit = new Tower(coordinate, id, isBlue);
                    break;
                }
                case "SwordMan": {
                    unit = new SwordMan(coordinate, id, isBlue);
                    break;
                }
                case "SpearMan": {
                    unit = new SpearMan(coordinate, id, isBlue);
                    break;
                }
            }
            units.add(unit);
            chart[coordinate.getX()][coordinate.getY()].setUnit(unit);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream printStream = new PrintStream("./testcase/out.txt");
        System.setOut(printStream);
        int n = scanner.nextInt();
        chart = new ChartCell[n][n];
        ArrayList<AbstractUnit> units = new ArrayList<>();
        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[i].length; j++) {
                chart[i][j] = new ChartCell(new Coordinate(i, j));
            }
        }
        int blueCount = scanner.nextInt();
        int redCount = scanner.nextInt();
        initChartUnits(blueCount, true, units);
        initChartUnits(redCount, false, units);

//        printChart();

        scanner.nextLine();

        int lvl = 1;
        while (true) {
            String in = scanner.nextLine();
            if (in.startsWith("tick ")) {
                int t = Integer.parseInt(in.substring(5));
                for (int i = 0; i < t; i++) {
                    tick();
                }
//                System.out.println(lvl);
//                printChart();
                lvl++;
                for (AbstractUnit unit : units) {
                    System.out.println(unit);
                }
            } else if (in.equalsIgnoreCase("terminate")) {
                break;
            }
        }
    }
}
