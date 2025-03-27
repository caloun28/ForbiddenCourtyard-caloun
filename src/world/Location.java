package world;

import entity.Npc;
import entity.items.Item;
import player.Player;

import java.util.HashMap;

/**
 * Parent class that makes locations.
 */
public abstract class Location {
    private String name;
    private String label;
    private boolean closed;
    private Npc npc;
    private String[] availableLocations;
    private Player player = new Player(100,10,true);
    private HashMap<String, Item> items;


    public Location(String name, String[] availableLocations, boolean closed, String label) {
        this.name = name;
        this.label = label;
        this.closed = closed;
        this.availableLocations = availableLocations;
        this.items = new HashMap<>();
    }


    public boolean isClosed() {
        return closed;
    }

    public String[] getAvailableLocations() {
        return availableLocations;
    }

    public String getName() {
        return name;
    }

    public void setNpc(Npc npc) {
        this.npc = npc;
    }

    public Npc getNpc() {
        return npc;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void addItem(Item item) {
        items.put(item.getName(),item);
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return name;
    }
}

