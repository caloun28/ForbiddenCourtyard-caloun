package entity.items;

public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TypeOfKey getKeyType() {
        return null;
    }

    public boolean isUsed() {
        return false;
    }

    public void setUsed(boolean used) {

    }

    @Override
    public String toString() {
        return name;
    }
}
