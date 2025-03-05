package entity.items;

public class Key extends Item {
    private TypeOfKey keyType;
    private boolean used;

    public Key(String name, boolean used, TypeOfKey keyType) {
        super(name);
        this.used = used;
        this.keyType = keyType;
    }

    public TypeOfKey getKeyType() {
        return keyType;
    }

    public boolean isUsed() {
        return used;
    }
}
