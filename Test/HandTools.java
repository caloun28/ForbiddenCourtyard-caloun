import static org.junit.jupiter.api.Assertions.*;

import entity.TypeOfNpc;
import org.junit.Before;
import player.Player;
import entity.Npc;
import entity.items.Axe;
import entity.items.Item;
import world.BoilerRoom;
import world.Location;
import command.console.Console;
import world.Workshop;
import org.junit.Test;

public class HandTools {
    private Console console;
    private Player player;
    private Location dilna;
    private Npc mistr;
    private command.HandTools handTools;

    @Before
    public void setUp() {
        console = new Console();
        player = console.getPlayer();
        dilna = new Workshop("dilna", new String[]{}, false, "workshop");
        mistr = new Npc(TypeOfNpc.FRIENDLY, "mistr", 100, 10, true);
        handTools = new command.HandTools(console);
    }

    @Test
    public void testNotInDilna() {
        Location boilerRoom = new BoilerRoom("kotelna", new String[]{}, false, "boiler room");
        player.setCurrentLocation(boilerRoom);

        String result = handTools.execute();
        assertEquals("Nejsi v dilne.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──", result);
    }

    @Test
    public void testMistrNotPresent() {
        player.setCurrentLocation(dilna);

        String result = handTools.execute();
        assertEquals("Mistr neni v teto mistnosti.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──", result);
    }

    @Test
    public void testNoTools() {
        dilna.setNpc(mistr);
        player.setCurrentLocation(dilna);

        String result = handTools.execute();
        assertEquals("Nemas kladivo ani sekeru.", result);
    }

    @Test
    public void testSuccessfulTrade() {
        dilna.setNpc(mistr);
        player.setCurrentLocation(dilna);

        Item tools = new Item("Kladivo") {};
        Axe axe = new Axe("Zachrana sekera", 5);
        player.getInventory().addItem(tools);
        player.getInventory().addItem(axe);

        String result = handTools.execute();
        assertEquals("Predal jsi mistrovi naradi a sekeru, kterou ti opravil a vratil zpatky.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──", result);
    }
}
