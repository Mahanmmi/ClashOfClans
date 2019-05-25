package logic;

import static logic.Main.chart;

public class Swordman extends AbstractSoldier {
    public Swordman(Coordinate coordinate, int id, boolean isBlue, int movementDelay) {
        super(coordinate, id, isBlue, 0, new ChainArmor(), new Sword(), 5000, 2);
    }

    @Override
    AttackAction attack(ChartCell[][] chart) {
        AttackAction action = super.attack(chart);
        attackDelay++;
        return action;
    }
}
