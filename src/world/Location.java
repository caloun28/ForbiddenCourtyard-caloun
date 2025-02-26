package world;

import java.util.Arrays;

public abstract class Location {
    private String name;
    private String label;
    private boolean closed;
    private boolean hasNpc;
    private String[] availableLocations;

    public Location(String name, String[] availableLocations, boolean closed, boolean hasNpc, String label) {
        this.name = name;
        this.label = label;
        this.closed = closed;
        this.hasNpc = hasNpc;
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

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", closed=" + closed +
                ", hasNpc=" + hasNpc +
                ", availableLocations=" + Arrays.toString(availableLocations) +
                '}';
    }
}

