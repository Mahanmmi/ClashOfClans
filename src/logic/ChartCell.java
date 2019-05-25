package logic;

import java.util.ArrayList;

public class ChartCell {
    private final Coordinate coordinate;
    private AbstractUnit unit;
    private ArrayList<AbstractSoldier> deadSoldiers;

    public ChartCell(Coordinate coordinate) {
        this(coordinate, null);
    }

    public ChartCell(Coordinate coordinate, AbstractUnit unit) {
        deadSoldiers = new ArrayList<>();
        this.coordinate = coordinate;
        this.unit = unit;
    }

    public void setUnit(AbstractUnit unit) {
        this.unit = unit;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public ArrayList<AbstractSoldier> getDeadSoldiers() {
        return deadSoldiers;
    }

    public AbstractUnit getUnit() {
        return unit;
    }
}
