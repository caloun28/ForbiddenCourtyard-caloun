package command.console;

import command.*;
import player.Player;
import world.Location;
import world.Map;

import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap();
    private Scanner scanner = new Scanner(System.in);
    private Player player = new Player(100,20,true);
    private Map gameMap;
    public Player getPlayer() {
        return player;
    }

    public Map getGameMap() {
        return gameMap;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public Console() {
        gameMap = new Map();
        gameMap.initialize();

        Location startingLocation = gameMap.getLocations().getFirst();

        if (startingLocation != null) {
            player.setCurrentLocation(startingLocation);
            System.out.println("Hráč začíná v lokaci: " + player.getCurrentLocation().getName());
        } else {
            System.out.println("Chyba: Nebyla nalezena žádná výchozí lokace!");
        }
    }

    public void inicialization() {
        map.put("jdi", new Movement(this));
        map.put("konec", new Exit());
        map.put("zautoc",new Attack(this));
        map.put("seber",new Take(this));
        map.put("vyhod", new Drop(this));
        map.put("odemkni", new Unlock(this));
        map.put("prozkoumej", new LookAround(this));
        map.put("podej", new HandTools(this));
        map.put("prikazy", new Help());
        map.put("pouzij", new Use(this));
        map.put("promluv si", new Talk(this));
        map.put("napoveda", new Hint(this));
    }

    private void doComm() {
        String command = scanner.nextLine().trim().toLowerCase();
        if (map.containsKey(command)) {
            String result = map.get(command).execute();
            System.out.println(result);
            if (!exit) {
                exit = map.get(command).exit();
            }
        } else {
            System.out.println("nedefinovany prikaz");
        }
    }

    public void start() {
        inicialization();
        try {
            do {
                System.out.print("ZADEJ COMMAND\n>> ");
                doComm();
            } while(!exit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

