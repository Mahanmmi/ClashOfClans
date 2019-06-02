package logic;

import java.util.ArrayList;

class ChartCell {
    private final Coordinate coordinate;
    private AbstractUnit unit;
    private ArrayList<AbstractSoldier> deadSoldiers;

    ChartCell(Coordinate coordinate) {
        this(coordinate, null);
    }

    ChartCell(Coordinate coordinate, AbstractUnit unit) {
        deadSoldiers = new ArrayList<>();
        this.coordinate = coordinate;
        this.unit = unit;
    }

    void setUnit(AbstractUnit unit) {
        this.unit = unit;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    ArrayList<AbstractSoldier> getDeadSoldiers() {
        return deadSoldiers;
    }

    public AbstractUnit getUnit() {
        return unit;
    }
}
