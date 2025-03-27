package entity.items;

/**
 * This class makes axe as an item.
 */
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
     * @param plusDamage Param 'plusDamage' is a parameter for property plusDamage of the axe.
     */
    public void setPlusDamage(int plusDamage) {
        if (plusDamage>0) {
            this.plusDamage = plusDamage;
        }
    }
}
