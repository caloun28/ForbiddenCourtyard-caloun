package command;

import command.console.Console;
import entity.Npc;
import entity.items.Axe;
import entity.items.Item;
import player.Player;
import world.Location;

/**
 * Class for handing tools to the npc.
 */
public class HandTools implements Command {
    private Console console;

    public HandTools(Console console) {
        this.console = console;
    }

    /**
     * With this method player can hand tools and axe to a workshop foreman
     * and with the tools he will repair the axe and hand it to you after.
     * But it will happen only if player has both items.
     * @return It returns an output for the given situation.
     */
    @Override
    public String execute() {
        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        Npc npc = currentLocation.getNpc();

        if (!currentLocation.getName().equalsIgnoreCase("dilna")) {
            return "Nejsi v dilne.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        }

        if (npc == null || !npc.getName().equalsIgnoreCase("mistr") || !npc.isAlive()) {
            return "Mistr neni v teto mistnosti.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        }

        if (!player.getInventory().getItems().containsKey("Kladivo") && !player.getInventory().getItems().containsKey("Zachrana sekera")) {
            return "Nemas kladivo nebo sekeru.";
        }else{
            Item tool = player.getInventory().getItems().get("Kladivo");
            Axe axe = (Axe) player.getInventory().getItems().get("Zachrana sekera");

            npc.addItem(tool);
            npc.addItem(axe);
            player.getInventory().removeItem("Zachrana sekera");
            player.getInventory().removeItem("Kladivo");

            axe.setPlusDamage(axe.getPlusDamage() + 20);
            player.increaseDamage(axe);

            if (player.getInventory().addItem(axe)) {
                npc.removeItem(axe);
                return "Predal jsi mistrovi naradi a sekeru, kterou ti opravil a vratil zpatky.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            } else {
                return "Nemas misto na opravenou sekeru\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            }

        }

    }

    @Override
    public boolean exit() {
        return false;
    }
}
