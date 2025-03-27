package command;

import command.console.Console;
import entity.Npc;
import entity.items.Bottle;
import entity.items.Medkit;
import player.Player;
import world.Location;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Use implements Command {
    private Scanner sc = new Scanner(System.in);
    private Console console;

    public Use(Console console) {
        this.console = console;
    }

    /**
     * Player can use an item that he has in his inventory and which he chose.
     * @return
     */
    @Override
    public String execute() {
        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        Npc npc = currentLocation.getNpc();

        if (!player.getInventory().getItems().isEmpty()) {
            System.out.print("Mas u sebe "+player.getInventory().getItems() + "\nCo chces pouzit\n>>");
            String input = sc.nextLine().toLowerCase().trim();

            if (!input.equals("mala lekarnicka") && !input.equals("zajimava lahvicka")) {
                return "Zadej nejaky predmet\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            }
            try {
                switch (input) {
                    case "mala lekarnicka":

                        if (player.getInventory().getItems().containsKey("Mala lekarnicka")) {
                            Medkit medkit = (Medkit) player.getInventory().getItems().get("Mala lekarnicka");
                            if (!medkit.isUsed()) {
                                player.increaseHealth(medkit);
                                return "Pouzil jsi lekarnicku a doplnili a zvedli se ti zivoty na " + player.getHealth() + "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                            } else {
                                return "Lekarnicku jsi uz pouzil\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                            }
                        } else {
                            return "Nemas u sebe lekarnicku\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                        }

                    case "zajimava lahvicka":
                        if (player.getInventory().getItems().containsKey("Zajimava lahvicka")) {
                            Bottle bottle = (Bottle) player.getInventory().getItems().get("Zajimava lahvicka");

                            if (bottle.isUsed()) {
                                return "Lahvicku jsi uz pouzil\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                            }

                            if (currentLocation.getNpc() != null) {
                                player.increaseEndurance(npc, bottle);
                                bottle.setUsed(true);
                                return "Pouzil jsi lahvicku a mas vetsi odolnost proti utokum\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                            } else {
                                return "Tady nemuzes lahvicku pouzit, pouzij ji a pred utokem\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                            }
                        } else {
                            return "Nemas u sebe lahvicku\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                        }
                }
            } catch (InputMismatchException e) {
                return "Zadej validni input";
            }
        } else {
            return "Nic u sebe nemas\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        }

        return player + "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
    }


    @Override
    public boolean exit() {
        return false;
    }
}
