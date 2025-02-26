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
            map.loadingMap();
            currentLocation = map.getLocations().getFirst();
        }

        System.out.println("Available locations: " + String.join(", ", currentLocation.getAvailableLocations()));
        System.out.println("Where do you want to move?\n>>");
        String input = scanner.nextLine();
        moveTo(input);
        return "";
    }

    public boolean exit() {
        return false;
    }

    public void moveTo(String name) {
        if (currentLocation.getName().equalsIgnoreCase(name)) {
            System.out.println("You are already in this location!");
        } else {
            for(String location : currentLocation.getAvailableLocations()) {
                if (location.equalsIgnoreCase(name)) {
                    for(Location loc : map.getLocations()) {
                        if (loc.getName().equalsIgnoreCase(name)) {
                            if (loc.isClosed()) {
                                System.out.println("This room is closed, try to find a key.");
                                return;
                            }

                            currentLocation = loc;
                            System.out.println("You moved to: " + currentLocation.getName());
                            return;
                        }
                    }
                }
            }

            System.out.println("You can't move there.");
        }
    }
}
