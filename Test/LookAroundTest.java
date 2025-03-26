import static org.junit.jupiter.api.Assertions.*;

import command.LookAround;
import command.console.Console;
import entity.Npc;
import entity.TypeOfNpc;
import entity.items.Key;
import entity.items.TypeOfKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;
import world.Cafeteria;
import world.Location;

class LookAroundTest {
    private Console console;
    private Player player;
    private Location location;
    private LookAround lookAround;

    @BeforeEach
    void setUp() {
        console = new Console();
        lookAround = new LookAround(console);
        player = new Player(100, 10, true);
        location = new Cafeteria("Jidelna", new String[]{}, false, "room");

        player.setCurrentLocation(location);
        console.setPlayer(player);
    }

    @Test
    void testEmptyRoom() {
        String expectedOutput = "Jsi v: Jidelna\nV mistnosti nidko neni.\nV mistnosti nejsou zadne predmety.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ───── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        assertEquals(expectedOutput, lookAround.execute());
    }

    @Test
    void testWithLivingNpc() {
        Npc npc = new Npc(TypeOfNpc.AGGRESSIVE, "Agresivni kucharka", 100, 10, true);
        location.setNpc(npc);

        String expectedOutput = "Jsi v: Jidelna\nV mistnosti je : Agresivni kucharka\nV mistnosti nejsou zadne predmety.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ───── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        assertEquals(expectedOutput, lookAround.execute());
    }

    @Test
    void testWithDeadNpc() {
        Npc npc = new Npc(TypeOfNpc.AGGRESSIVE, "Agresivni kucharka", 100, 10, false);
        location.setNpc(npc);

        String expectedOutput = "Jsi v: Jidelna\nV mistnosti je mrtvy/a: Agresivni kucharka\nV mistnosti nejsou zadne predmety.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ───── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        assertEquals(expectedOutput, lookAround.execute());
    }

    @Test
    void testWithItems() {
        location.addItem(new Key("klic od kabinetu", false, TypeOfKey.FOR_CABINET));

        String expectedOutput = "Jsi v: Jidelna\nV mistnosti nidko neni.\nV mistnosti jsou tyto predmety: - klic od kabinetu\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        assertEquals(expectedOutput, lookAround.execute());
    }
}