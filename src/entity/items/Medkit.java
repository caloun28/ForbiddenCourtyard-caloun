package entity.items;

/**
 * This class is inherited class from item.
 */
public class Medkit extends Item {
    private boolean used;
    private int plusHealth;

    public Medkit(String name,boolean used,int plusHealth) {
        super(name);
        this.used = used;
        this.plusHealth = plusHealth;
    }

    public boolean isUsed() {
        return used;
    }

    public int getPlusHealth() {
        return plusHealth;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
