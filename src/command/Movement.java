package command;

import java.util.Scanner;
import world.Location;
import world.Map;

public class Movement implements Command {
    private Scanner scanner = new Scanner(System.in);
    private Map map = new Map();
    private Location currentLocation;


    public String execute() {
        if (currentLocation == null) {
            map.initialize();
            currentLocation = map.getLocations().getFirst();

        }
        System.out.println("Dostupne lokace: " + String.join("," , currentLocation.getAvailableLocations()) + "\nKam chces jit?\n>>");
        String input = scanner.nextLine();
        System.out.println(moveTo(input) + "\n>>");
        return "";
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
                            if (loc.isClosed()) {
                                return "Tato mistnost je zamcena. Jsi furt na chodbe";
                            }

                            currentLocation = loc;
                            return "Posunul jsi se do: " + currentLocation.getName() + " a je tu " + loc.getNpc().getName();
                        }
                    }
                }
            }

        }
        return "Neco je spatne";
    }


}
