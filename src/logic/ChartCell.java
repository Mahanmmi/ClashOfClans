package logic;

public class ChartCell {
    private final Coordinate coordinate;
    private AbstractUnit unit;

    public ChartCell(Coordinate coordinate) {
        this(coordinate, null);
    }

    public ChartCell(Coordinate coordinate, AbstractUnit unit) {
        this.coordinate = coordinate;
        this.unit = unit;
    }

    public void setUnit(AbstractUnit unit) {
        this.unit = unit;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public AbstractUnit getUnit() {
        return unit;
    }
}
