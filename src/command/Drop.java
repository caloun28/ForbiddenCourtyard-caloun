package command;

import command.console.Console;
import entity.items.Item;
import player.Player;
import world.Location;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class for dropping item.
 */
public class Drop implements Command {
    private Console console;
    private Scanner sc = new Scanner(System.in);

    public Drop(Console console) {
        this.console = console;
    }

    /**
     * Thanks to this method player can drop item that he has in his inventory.
     * @return It returns an output for the given situation.
     */
    @Override
    public String execute() {

        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();

        try {
            if(player.getInventory().getItems().isEmpty()) {
                return "Nemas nic v inventari\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            }

            System.out.print("Mas u sebe " + player.getInventory().getItems() + "\nZadej co chces vyhodit\n>> ");
            String input = sc.nextLine().trim().toLowerCase();
            Item itemToDrop = null;

            for (String itemName : player.getInventory().getItems().keySet()) {
                if (itemName.toLowerCase().equals(input)) {
                    itemToDrop = player.getInventory().getItems().get(itemName);
                    break;
                }
            }

            if (itemToDrop == null) {
                return "Nemas tento predmet\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            }

            currentLocation.addItem(itemToDrop);
            player.getInventory().removeItem(itemToDrop.getName());

        } catch (InputMismatchException e) {
            return "Zadej spravny item\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        }

        return player + "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
