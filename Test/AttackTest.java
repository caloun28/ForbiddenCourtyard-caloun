
import command.Attack;
import command.console.Console;
import entity.Npc;
import entity.TypeOfNpc;
import entity.items.Key;
import entity.items.TypeOfKey;
import org.junit.Before;
import org.junit.Test;
import player.Player;
import world.Cafeteria;
import world.Location;

import static org.junit.Assert.*;


public class AttackTest {
    private Console console;
    private Player player;
    private Npc npc;
    private Location location;
    private Attack attack;
    private Key key;

    @Before
    public void setUp() throws Exception {
        console = new Console();
        location = new Cafeteria("jidelna", new String[]{}, false, "jidelna");
        player = new Player(100,20,true);
        key = new Key("klic od kabinetu", false, TypeOfKey.FOR_CABINET);
        player.setCurrentLocation(location);
        console.setPlayer(player);
        attack = new Attack(console);
        npc = new Npc(TypeOfNpc.AGGRESSIVE,"Agresivni kucharka",100,10,true);
        npc.setCurrentLocation(location);
        location.setNpc(npc);
        npc.addItem(key);

    }

    @Test
    public void testNoNpc() {
        location.setNpc(null);
        String output = "V teto lokaci neni zadne NPC, na ktere bys mohl zautocit!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        assertEquals(output, attack.execute());
    }

    @Test
    public void testPlayerIsDead() {

        player.setAlive(false);
        String output = "Nemuzes utocit, jsi mrtvy!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        assertEquals(output, attack.execute());
    }

    @Test
    public void testNpcIsDead() {
        npc.setAlive(false);
        String output = "Toto NPC už je mrtve!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        assertEquals(output, attack.execute());
    }

    @Test
    public void testNpcWasKilled() {
        player.setAlive(true);
        npc.setAlive(true);

        String result = attack.execute();

        assertTrue(result.contains("Zacina souboj s " + npc.getName()));
        assertTrue(result.contains(npc.getName() + " BYL/A PORAZEN/A!"));
        assertTrue(result.contains("Z " + npc.getName() +" vypadly tyto predmety:"));
        assertTrue(result.contains("- klic od kabinetu"));
        assertTrue(result.contains("Obnovili se ti zivoty na max"));

        assertFalse(npc.isAlive());

        assertTrue(player.isAlive());
    }

    @Test
    public void testPlayerDied() {
        player.setHealth(10);
        player.setAlive(true);
        npc.setHealth(100);
        npc.setAlive(true);

        String result = attack.execute();

        assertTrue(result.contains("Zacina souboj s "+ npc.getName()));
        assertTrue(result.contains("ZEMREL JSI!"));

        assertFalse(player.isAlive());

        assertTrue(npc.isAlive());
    }

    @Test
    public void testBossDied() {
        npc = new Npc(TypeOfNpc.AGGRESSIVE, "Nemrtvy reditel", 250,25,true);
        location.setNpc(npc);
        npc.setCurrentLocation(location);
        npc.setAlive(true);

        player.setHealth(1000);
        player.setAlive(true);

        String result = attack.execute();

        assertTrue(result.contains("Zacina souboj s "+ npc.getName()));
        assertTrue(result.contains("PORAZIL JSI NEMRTVEHO REDITELE. DIKY TOMU JSI OSVOBODIL STUDENTA, PROFESORA A MISTRA. VSICHNI JSTE UTEKLI PRYC🎊🎉✨   "));

        assertFalse(npc.isAlive());

        assertTrue(player.isAlive());
    }

}