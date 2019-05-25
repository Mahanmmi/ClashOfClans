package logic;

import static logic.Main.chart;

public class AttackAction implements Action{
    private final AbstractUnit unit;
    private final AbstractSoldier target;

    public AttackAction(AbstractUnit unit, AbstractSoldier target) {
        this.unit = unit;
        this.target = target;
    }

    @Override
    public void doAction() {
        target.takeDamage(unit.getWeapon().getDamage());
        unit.setCurrentAttackDelay(unit.getAttackDelay());
        if(!target.isAlive()){
            chart[target.getCoordinate().getX()][target.getCoordinate().getY()].setUnit(null);
            chart[target.getCoordinate().getX()][target.getCoordinate().getY()].getDeadSoldiers().add(target);
        }
    }
}
