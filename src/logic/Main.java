package logic;

import java.util.Scanner;

public class Main {
    static ChartCell[][] chart;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        chart = new ChartCell[n][n];
    }
}
