import entity.Npc;
import entity.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import world.Location;
import world.Map;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    private Map map;
    @BeforeEach
    public void setUp() {
        map = new Map();
        map.initialize();
    }

    @Test
    public void testNpcAssignment() {
        for (Location location : map.getLocations()) {
            if (location.getNpc() != null) {
                System.out.println("Lokace " + location.getName() + " obsahuje NPC: " + location.getNpc().getName());
                assertNotNull(location.getNpc(),"NPC by melo byt priřazeno k lokaci " + location.getName());
            }
        }
    }

    @Test
    public void testCorrectItemsAssignedToNpc() {
        HashSet<String> checkedNpcs = new HashSet<>();

        for (Location location : map.getLocations()) {
            Npc npc = location.getNpc();

            if (npc != null && !npc.getItems().isEmpty()) {

                if (!checkedNpcs.contains(npc.getName())) {
                    checkedNpcs.add(npc.getName());

                    HashSet<Item> uniqueItems = new HashSet<>(npc.getItems());

                    for (Item item : uniqueItems) {
                        assertNotNull(item,"Predmet by mel byt priřazen NPC " + npc.getName());
                        System.out.println("NPC " + npc.getName() + " ma priřazený predmet: " + item.getName());
                    }
                }
            }
        }
    }

    @Test
    public void testAllLocations() {

        for (Location location : map.getLocations()) {
            assertNotNull(location.getName(),"Nazev lokace by nemel byt null");
            assertFalse(location.getName().isEmpty(), "Nazev lokace by nemel byt prazdny");

            assertNotNull(location.getLabel(),"Popis lokace by nemel byt null");

            boolean isClosed = location.isClosed();
            if (isClosed) {
                assertTrue(true, "Uzamcena lokace by mela mit spravne nastaveny stav uzamceni");
            } else {
                assertFalse(false, "Otevrena lokace by nemela byt uzamcena");
            }

            String[] availableLocations = location.getAvailableLocations();
            assertNotNull( availableLocations,"Dostupne lokace by nemely byt null");
            assertTrue(availableLocations.length > 0,"Kazda lokace by mela mit alespon jednu dostupnou sousedni lokaci");
        }
    }
}