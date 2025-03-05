package world;

import entity.Npc;
import entity.TypeOfNpc;
import entity.items.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private ArrayList<Location> locations = new ArrayList();
    private ArrayList<String[]> availableLocations = new ArrayList();

    public void initialize() {
        loadingMap();
        loadItems();
        loadNpc();
    }

    public void loadNpc() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("npc.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] lines = line.split("/");
                TypeOfNpc type = TypeOfNpc.valueOf(lines[0]);
                String name = lines[1];
                int health = Integer.parseInt(lines[2]);
                int damage = Integer.parseInt(lines[3]);
                boolean alive = Boolean.parseBoolean(lines[4]);
                String locationName = lines[5];

                Npc npc = new Npc(type, name, health, damage, alive);

                for (Location location : locations) {
                    if (location.getName().equals(locationName)) {
                        location.setNpc(npc);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong");
        }
    }

    public void loadItems(){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("items.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] lines = line.split("/");
                String itemType = lines[0];
                String name = lines[1];
                String npcName = lines[lines.length - 1];
                boolean used = Boolean.parseBoolean(lines[2]);
                Item item = null;

                switch (itemType) {
                    case "Sekera":
                        int plusDamage = Integer.parseInt(lines[2]);
                        item = new Axe(name, plusDamage);
                        break;
                    case "Klic":
                        TypeOfKey typeOfKey = TypeOfKey.valueOf(lines[3]);
                        item = new Key(name, used, typeOfKey);
                        break;
                    case "Naradi":
                        item = new Tools(name, used);
                        break;
                    case "Lekarnicka":
                        int plusHealth = Integer.parseInt(lines[3]);
                        item = new Medkit(name, used, plusHealth);
                        break;
                    case "Lahvicka":
                        int quality = Integer.parseInt(lines[3]);
                        item = new Bottle(name, used, quality);
                        break;
                }

                if (item != null) {
                    for (Location location : locations) {
                        Npc npc = location.getNpc();

                        if (npc!= null && npc.getName().equals(npcName)) {
                            npc.addItem(item);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong");
        }
    }

    public void loadingMap() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("map.csv"))) {
            while((line = br.readLine()) != null) {
                String[] lines = line.split("/");

                String name = lines[0];

                String label = lines[lines.length - 1];

                boolean closed = Boolean.parseBoolean(lines[lines.length - 3]);



                String[] availableLoc = Arrays.copyOfRange(lines, 1, lines.length - 2);

                availableLocations.add(availableLoc);
                switch (name) {
                    case "Chodba":
                        locations.add(new Hallway(name, availableLoc, closed, label));
                        break;
                    case "Jidelna":
                        locations.add(new Cafeteria(name, availableLoc, closed, label));
                        break;
                    case "Trida 7":
                        locations.add(new Classroom7(name, availableLoc, closed, label));
                        break;
                    case "Trida 28":
                        locations.add(new Classroom28(name, availableLoc, closed, label));
                        break;
                    case "Kabinet 666":
                        locations.add(new Cabinet666(name, availableLoc, closed, label));
                        break;
                    case "Kotelna":
                        locations.add(new BoilerRoom(name, availableLoc, closed, label));
                        break;
                    case "WC":
                        locations.add(new Restrooms(name, availableLoc, closed, label));
                        break;
                    case "Sborovna":
                        locations.add(new StaffRoom(name, availableLoc, closed, label));
                        break;
                    case "Dilna":
                        locations.add(new Workshop(name, availableLoc, closed, label));
                        break;
                    case "Dvur":
                        locations.add(new Courtyard(name, availableLoc, closed,label));
                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong");
        }

    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        return "Map{" +
                "availableLocations=" + availableLocations +
                '}';
    }
}