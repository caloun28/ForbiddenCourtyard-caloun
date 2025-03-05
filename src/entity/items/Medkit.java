package entity.items;

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
}
