package world;
/**
 * Inherited class that represents room.
 */
public class Workshop extends Location{

    public Workshop(String name, String[] availableLocations, boolean closed,  String label) {
        super(name, availableLocations, closed, label);
    }
}
