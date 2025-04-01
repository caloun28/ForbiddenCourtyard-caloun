package command;

import command.console.Console;
import entity.items.Item;
import player.Player;
import world.Location;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is for controlling that player can take the item.
 */
public class Take implements Command {
    private Console console;
    private Scanner sc = new Scanner(System.in);
    public Take(Console console) {
        this.console = console;
    }

    /**
     * This method detects if there is an object in this room. If so, the player can select this object
     * and put it in his inventory.
     * @return It returns an output for the given situation.
     */
    @Override
    public String execute() {
        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();

       try {

           if(currentLocation.getItems().isEmpty()){
               return "V teto mistnosti nemuzes nic sebrat\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
           }

           System.out.print("Je tady:" + currentLocation.getItems() + "\nZadej co chces sebrat\n>> ");
           String input = sc.nextLine().trim().toLowerCase();
           Item itemToTake = null;

           for (String itemName : currentLocation.getItems().keySet()){
               if(itemName.toLowerCase().equals(input)){
                   itemToTake = currentLocation.getItems().get(itemName);
                   break;
               }
           }

           if (itemToTake == null) {
               return "Tento predmet tu neni\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
           }

           if (!player.getInventory().addItem(itemToTake)) {
               return "Nemuzes mit vic nez 3 veci\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
           }else{
               currentLocation.getItems().remove(itemToTake.getName());
           }



       }catch (InputMismatchException e){
           return "Zadej spravny item\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
       }



        return player + "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
