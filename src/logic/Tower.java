package logic;

import java.util.ArrayList;

import static logic.Main.chart;

public class Tower extends AbstractUnit {
    public Tower(Coordinate coordinate, int id, boolean isBlue) {
        super("Tower",10000,coordinate, id, isBlue, 10, new StoneArmor(), new Sling());
    }

    private ArrayList<Action> attack(ChartCell[][] chart) {
        ArrayList<Action> attackActions = new ArrayList<>();
        if (currentAttackDelay == 0) {
            ArrayList<Coordinate> targetsCoordinates = weapon.canHit(chart, isBlue(), this.coordinate, direction);
            for (Coordinate targetCoordinate : targetsCoordinates) {
                AbstractSoldier target = (AbstractSoldier) chart[targetCoordinate.getX()][targetCoordinate.getY()].getUnit();
                if (target != null && !target.getType().equals("Tower") && target.isBlue() != isBlue())
                    attackActions.add(new AttackAction(this, target, this.getCoordinate()));
            }
            return attackActions;
        }
        return attackActions;
    }

    @Override
    ArrayList<Action> act() {
        return (attack(chart));
    }
}
