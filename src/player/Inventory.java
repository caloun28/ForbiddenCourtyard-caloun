package player;

import entity.items.Item;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Item> inventory;

    public void addItem(Item item) {
        inventory.put(item.getName(), item);
    }

    public void removeItem(Item item) {
        inventory.remove(item.getName());
    }
}
