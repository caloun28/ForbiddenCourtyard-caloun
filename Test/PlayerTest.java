import entity.Npc;
import entity.TypeOfNpc;
import entity.items.Axe;
import entity.items.Bottle;
import entity.items.Medkit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;
    private Npc npc;
    private Axe axe;
    private Medkit medkit;
    private Bottle bottle;

    @BeforeEach
    public void setUp() {
        player = new Player(100, 10, true);
        npc = new Npc(TypeOfNpc.AGGRESSIVE,"Test NPC", 100, 20, true);
        axe = new Axe("Zachrana sekera", 5);
        medkit = new Medkit("Mala lekarnicka", false, 75);
        bottle = new Bottle("Zajimava lahvicka", false, 2);
    }


    @Test
    public void testIncreaseDamage() {
        int initialDamage = player.getDamage();
        player.increaseDamage(axe);
        assertEquals(initialDamage + axe.getPlusDamage(), player.getDamage(), "Poskozeni by melo byt zvyseno o bonus sekery.");
    }


    @Test
    public void testIncreaseHealth() {
        int initialHealth = player.getHealth();
        player.increaseHealth(medkit);
        assertEquals(initialHealth + medkit.getPlusHealth(), player.getHealth(), "Zdravi hrace by melo byt zvyseno o hodnotu z medikitu.");
        assertTrue(medkit.isUsed(), "Medkit by mel byt oznacen jako pouzity.");
    }


    @Test
    public void testIncreaseHealthMaxHealth() {
        player.setMaxHealth(120);
        player.setHealth(100);
        player.increaseHealth(medkit);
        assertEquals(175, player.getHealth(), "Zdravi hrace by nemelo presahnout maximalni hodnotu.");
    }

    @Test
    public void testIncreaseEndurance() {
        int npcDamage = npc.getDamage();
        player.increaseEndurance(npc, bottle);
        assertEquals(npcDamage / bottle.getQuality(), npc.getDamage(), "Poskozeni NPC by melo byt snizeno podle kvality lahve.");
        assertTrue(bottle.isUsed(), "Lahvinka by mela byt oznacena jako pouzita.");
    }
}