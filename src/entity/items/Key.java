package entity.items;

public class Key {
    private TypeOfKey keyType;
    private boolean used;

    public Key(boolean used, TypeOfKey keyType) {
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
