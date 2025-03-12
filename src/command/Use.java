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

    @Override
    public String execute() {
        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        Npc npc = currentLocation.getNpc();

        if (!player.getInventory().getItems().isEmpty()) {
            System.out.print("Co chces pouzit\n>>");
            String input = sc.nextLine().toLowerCase().trim();
            try {
                switch (input) {
                    case "mala lekarnicka":

                        if (player.getInventory().getItems().containsKey("Mala lekarnicka")) {
                            Medkit medkit = (Medkit) player.getInventory().getItems().get("Mala lekarnicka");
                            if (!medkit.isUsed()) {
                                player.increaseHealth(medkit);
                                return "Pouzil jsi lekarnicku a doplnili a zvedli se ti zivoty na " + player.getHealth();
                            } else {
                                return "Lekarnicku jsi uz pouzil";
                            }
                        } else {
                            return "Nemas u sebe lekarnicku";
                        }

                    case "zajimava lahvicka":

                        if (player.getInventory().getItems().containsKey("Zajimava lahvicka")) {
                            Bottle bottle = (Bottle) player.getInventory().getItems().get("Zajimava lahvicka");
                            if(!bottle.isUsed()) {
                            player.increaseEndurance(npc, bottle);

                            return "Pouzil jsi lahvicku a mas vetsi odolnost proti utokum";
                            }else{
                                return "Lahvicku jsi uz pouzil";
                            }
                        } else {
                            return "Nemas u sebe lahvicku";
                        }
                }
            } catch (InputMismatchException e) {
                return "Zadej validni input";
            }
        } else {
            return "Nic u sebe nemas";
        }
        return player.toString();
    }


    @Override
    public boolean exit() {
        return false;
    }
}
