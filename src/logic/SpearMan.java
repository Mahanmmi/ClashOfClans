package logic;

public class SpearMan extends AbstractSoldier {
    public SpearMan(Coordinate coordinate, int id, boolean isBlue) {
        super("SpearMan",coordinate, id, isBlue, 0, new ChainArmor(), new Spear(), 3000, 1);
    }
}
