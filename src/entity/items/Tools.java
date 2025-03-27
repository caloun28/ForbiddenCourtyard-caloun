package entity.items;

/**
 * This class represents tools as an item.
 */
public class Tools extends Item{
    private boolean used;

    public Tools(String name, boolean used) {
        super(name);
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }
}
