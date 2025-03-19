package entity.items;

public class Bottle extends Item {
    private boolean used;
    private int quality;

    public Bottle(String name, boolean used, int quality) {
        super(name);
        this.used = used;
        this.quality = quality;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getQuality() {
        return quality;
    }

}
