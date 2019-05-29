package logic;

import static logic.Main.chart;

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
}
