package logic;

class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveTo(int x, int y) {
        moveTo(new Coordinate(x,y));
    }

    public void moveTo(Coordinate coordinate) {
        x = coordinate.getX();
        y = coordinate.getY();
    }

    @Override
    public int hashCode() {
        return x * 119 + y * 117;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            return x == ((Coordinate) obj).x && y == ((Coordinate) obj).y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" +
                "" + x +
                "," + y +
                ')';
    }
}
