package command;

import command.console.Console;
import entity.items.Item;
import player.Player;
import world.Location;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Take implements Command {
    private Console console;
    private Scanner sc = new Scanner(System.in);
    public Take(Console console) {
        this.console = console;
    }

    @Override
    public String execute() {
        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();


       try {
           if(currentLocation.getItems().isEmpty()){
               return "V teto mistnosti nemuzes nic sebrat";
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
               return "Tento predmet tu neni";
           }

           if (!player.getInventory().addItem(itemToTake)) {
               return "Nemuzes mit vic nez 3 veci";
           }else{
               currentLocation.getItems().remove(itemToTake.getName());
           }



       }catch (InputMismatchException e){
           return "Zadej spravny item";
       }



        return player.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
