package logic;

import static logic.Main.chart;

class AttackAction implements Action {
    private final AbstractUnit unit;
    private final AbstractUnit target;
    private final Coordinate coordinate;

    AttackAction(AbstractUnit unit, AbstractUnit target, Coordinate coordinate) {
        this.unit = unit;
        this.target = target;
        this.coordinate = coordinate;
    }

    @Override
    public void doAction() {
        if(target instanceof AbstractSoldier) {
            AbstractSoldier target = (AbstractSoldier) this.target;
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
                target.getCoordinate().moveTo(-1, -1);
            }
        }
    }
}
