package command;

import command.console.Console;
import entity.Npc;
import entity.items.Item;
import player.Player;
import world.Location;

/**
 * Attack class is a command for attacking feature in the game.
 */
public class Attack implements Command {
    private Console console;
    public Attack(Console console) {
        this.console = console;
    }

    /**
     * This method is for dual attack between player and given npc to the room. If the player wins the duel
     * an item will be dropped from the npc and player will be healed to max health. IF the defeated npc is
     * the final boss, game will end.
     * @return It returns an output for the given situation.
     */
    @Override
    public String execute() {

        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        Npc npc = currentLocation.getNpc();

        if (npc == null) {
                return "V teto lokaci neni zadne NPC, na ktere bys mohl zautocit!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        }
        if (!player.isAlive()) {
            return "Nemuzes utocit, jsi mrtvy!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        }
        if (!npc.isAlive()) {
            return "Toto NPC už je mrtve!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        }

        StringBuilder text = new StringBuilder();
        text.append("Zacina souboj s ").append(npc.getName()).append("\n");

        while (npc.isAlive() && player.isAlive()) {
            player.attack(npc);
            text.append("Zasahl jsi ").append(npc.getName()).append(" a ma ").append(npc.getHealth()).append(" zivotu.\n");

            if (!npc.isAlive()) {

                if (npc.getName().equalsIgnoreCase("Nemrtvy reditel")) {
                    text.append("\nPORAZIL JSI NEMRTVEHO REDITELE. DIKY TOMU JSI OSVOBODIL STUDENTA, PROFESORA A MISTRA. VSICHNI JSTE UTEKLI PRYC🎊🎉✨ \n");
                    console.setExit(true);
                    return text.toString();
                }
                text.append(npc.getName()).append(" BYL/A PORAZEN/A!\n");
                if (!npc.getItems().isEmpty()) {
                    text.append("Z " + npc.getName() + " vypadly tyto predmety:");

                    for (Item item : npc.getItems()) {
                        currentLocation.addItem(item);
                        text.append("- " + item.getName()+"\n");
                    }

                    npc.getItems().clear();
                }
                player.healToMax();
                text.append("Obnovili se ti zivoty na max\n");
                break;
            }

            npc.attack(player);
            text.append(npc.getName()).append(" te zasahl. Zbyvá ti ").append(player.getHealth()).append(" zivotu.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── \n");

            if (!player.isAlive()) {
                    text.append("ZEMREL JSI!");
                console.setExit(true);
                return text.toString();
            }
        }

        return text + "─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
