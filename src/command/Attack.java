package command;

import command.console.Console;
import entity.Npc;
import entity.items.Item;
import player.Player;
import world.Location;

public class Attack implements Command {
    private Console console;
    public Attack(Console console) {
        this.console = console;
    }

    @Override
    public String execute() {

        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        Npc npc = currentLocation.getNpc();

        if (npc == null) {
                return "V této lokaci není žádné NPC, na které bys mohl zaútočit!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        }
        if (!player.isAlive()) {
            return "Nemůžeš útočit, jsi mrtvý!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        }
        if (!npc.isAlive()) {
            return "Toto NPC už je mrtvé!\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
        }

        StringBuilder text = new StringBuilder();
        text.append("Začíná souboj s ").append(npc.getName()).append("\n");

        while (npc.isAlive() && player.isAlive()) {
            player.attack(npc);
            text.append("Zasáhl jsi ").append(npc.getName()).append(" a má ").append(npc.getHealth()).append(" životů.\n");

            if (!npc.isAlive()) {

                if (npc.getName().equalsIgnoreCase("Nemrtvy reditel")) {
                    text.append("\nPORAZIL JSI NEMRTVEHO REDITELE. HRA KONCI\n");
                    console.setExit(true);
                    return text.toString();
                }
                text.append(npc.getName()).append(" BYL/A PORAŽEN/A!\n");
                if (!npc.getItems().isEmpty()) {
                    text.append("Z " + npc.getName() + " vypadly tyto předměty:");

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
            text.append(npc.getName()).append(" tě zasáhl. Zbývá ti ").append(player.getHealth()).append(" životů.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── \n");

            if (!player.isAlive()) {
                text.append("ZEMŘEL JSI!\n");
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
