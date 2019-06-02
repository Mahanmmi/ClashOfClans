package logic;

import static logic.Main.chart;

class MovementAction implements Action {
    private final AbstractSoldier soldier;
    private final Coordinate coordinate;

    MovementAction(AbstractSoldier soldier, Coordinate coordinate) {
        this.soldier = soldier;
        this.coordinate = coordinate;
    }

    @Override
    public void doAction() {
        chart[soldier.getCoordinate().getX()][soldier.getCoordinate().getY()].setUnit(null);
        soldier.getCoordinate().moveTo(coordinate);
        chart[soldier.getCoordinate().getX()][soldier.getCoordinate().getY()].setUnit(soldier);
        soldier.setCurrentMovementDelay(soldier.getMovementDelay());
    }
}
