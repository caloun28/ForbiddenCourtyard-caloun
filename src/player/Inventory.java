package player;

import entity.items.Item;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Item> inventory = new HashMap<>();
    private int maxItems = 3;

    /**
     * Adds items to an inventory until it reaches it's limit which is 3 items.
     * @param item
     * @return
     */
    public boolean addItem(Item item) {
        if (inventory.size() >= maxItems) {
            return false;
        }
        inventory.put(item.getName(), item);
        return true;
    }

    public HashMap<String, Item> getItems() {
        return inventory;
    }

    public void removeItem(String itemName) {
        inventory.remove(itemName);
    }

    @Override
    public String toString() {
        return "Predmety : " + inventory;
    }
}
