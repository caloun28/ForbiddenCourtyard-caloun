package entity.items;

public class Bottle extends Item {
    private boolean used;
    private int quality;
    public Bottle(String name, boolean used, int quanlity) {
        super(name);
        this.used = used;
        this.quality = quanlity;
    }

    public boolean isUsed() {
        return used;
    }

    public int getQuality() {
        return quality;
    }
}
