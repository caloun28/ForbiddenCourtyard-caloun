package world;

import entity.Npc;

import java.util.Arrays;

public abstract class Location {
    private String name;
    private String label;
    private boolean closed;
    private Npc npc;
    private String[] availableLocations;

    public Location(String name, String[] availableLocations, boolean closed,String label) {
        this.name = name;
        this.label = label;
        this.closed = closed;
        this.availableLocations = availableLocations;
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

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", closed=" + closed +
                ", npc=" + npc +
                ", availableLocations=" + Arrays.toString(availableLocations) +
                '}';
    }
}

