package command;

import java.util.Scanner;

import command.console.Console;
import player.Player;
import world.Location;
import world.Map;

public class Movement implements Command {
    private Scanner scanner = new Scanner(System.in);
    private Map map;
    private Location currentLocation;
    private Player player;

    public Movement(Console console) {
        this.player = console.getPlayer();
        this.map = console.getGameMap();
    }

    public String execute() {

        if (currentLocation == null) {
            map.initialize();
            currentLocation = map.getLocations().getFirst();
            player.moveTo(currentLocation);
        }

        System.out.print("Dostupné lokace: " + String.join(", ", currentLocation.getAvailableLocations()) + "\nKam chceš jít?\n>> ");

        try {
            return moveTo(scanner.next())+ "\n"  + player.toString() +"\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        }catch(Exception e) {
            return "Zadej spravnou mistnost";
        }
    }

    public boolean exit() {
        return false;
    }

    public String moveTo(String name) {
        if (currentLocation.getName().equalsIgnoreCase(name)) {
            return "Jsi v teto mistnosti!";
        } else {
            for(String location : currentLocation.getAvailableLocations()) {
                if (location.equalsIgnoreCase(name)) {
                    for(Location loc : map.getLocations()) {
                            if (loc.getName().equalsIgnoreCase(name)) {
                                    if(!loc.isClosed()){
                                        player.moveTo(loc);
                                        currentLocation = loc;
                                        return "Posunul jsi se do: " + currentLocation;

                                    }else{
                                        return "Tato mistnost je zamcena";
                                    }

                            }
                    }
                }
            }

        }
        return "Neco je spatne";
    }


}
