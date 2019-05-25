package logic;

import static logic.Main.chart;

public class AttackAction implements Action {
    private final AbstractUnit unit;
    private final AbstractSoldier target;
    private final Coordinate coordinate;

    public AttackAction(AbstractUnit unit, AbstractSoldier target, Coordinate coordinate) {
        this.unit = unit;
        this.target = target;
        this.coordinate = coordinate;
    }

    @Override
    public void doAction() {
        target.takeDamage(unit.getWeapon().getDamage());
        unit.setCurrentAttackDelay(unit.getAttackDelay());
        if (!target.isAlive()) {
            chart[target.getCoordinate().getX()][target.getCoordinate().getY()].setUnit(null);
            chart[target.getCoordinate().getX()][target.getCoordinate().getY()].getDeadSoldiers().add(target);
            if (unit instanceof AbstractSoldier) {
                chart[unit.getCoordinate().getX()][unit.getCoordinate().getY()].setUnit(null);
                unit.getCoordinate().moveTo(coordinate);
                chart[unit.getCoordinate().getX()][unit.getCoordinate().getY()].setUnit(unit);
                ((AbstractSoldier) unit).setCurrentMovementDelay(((AbstractSoldier) unit).getMovementDelay());
            }
        }
    }
}
