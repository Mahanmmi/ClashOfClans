package logic;

abstract class AbstractArmor {
    private final int resistance;
    private final int weight;

    AbstractArmor(int resistance, int weight) {
        this.resistance = resistance;
        this.weight = weight;
    }

    int getWeight() {
        return weight;
    }

    int takeDamage(int damage){
        return (damage*(100-resistance))/100;
    }
}
