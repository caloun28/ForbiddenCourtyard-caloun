package command;

import command.console.Console;
import entity.items.Item;
import player.Player;
import world.Location;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Drop implements Command {
    private Console console;
    private Scanner sc = new Scanner(System.in);

    public Drop(Console console) {
        this.console = console;
    }

    @Override
    public String execute() {

        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        try {
            if(player.getInventory().getItems().isEmpty()) {
                return "Nemas nic v inventari";
            }

            System.out.print("Zadej co chces vyhodit\n>> ");
            String input = sc.nextLine().trim().toLowerCase();
            Item itemToDrop = null;

            for (String itemName : player.getInventory().getItems().keySet()) {
                if (itemName.toLowerCase().equals(input)) {
                    itemToDrop = player.getInventory().getItems().get(itemName);
                    break;
                }
            }

            if (itemToDrop == null) {
                return "Nemas tento predmet";
            }

            currentLocation.addItem(itemToDrop);
            player.getInventory().removeItem(itemToDrop.getName());

        } catch (InputMismatchException e) {
            return "Zadej spravny item";
        }

        return player.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
