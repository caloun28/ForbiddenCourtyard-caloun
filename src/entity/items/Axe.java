package entity.items;

public class Axe extends Item{
    private int plusDamage;

    public Axe(String name, int plusDamage) {
        super(name);
        this.plusDamage = plusDamage;
    }

    public int getPlusDamage() {
        return plusDamage;
    }

    /**
     * It prevents the axe from having damage below 0
     * @param plusDamage
     */
    public void setPlusDamage(int plusDamage) {
        if (plusDamage>0) {
            this.plusDamage = plusDamage;
        }
    }
}
