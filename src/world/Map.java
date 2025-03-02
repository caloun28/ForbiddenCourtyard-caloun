package world;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private ArrayList<Location> locations = new ArrayList();
    private ArrayList<String[]> availableLocations = new ArrayList();



    public void loadingMap() {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("map.csv"))) {
            while((line = br.readLine()) != null) {
                String[] lines = line.split("/");

                String name = lines[0];

                String label = lines[lines.length - 1];

                boolean closed = Boolean.parseBoolean(lines[lines.length - 3]);

                boolean hasNpc = Boolean.parseBoolean(lines[lines.length - 2]);

                String[] availableLoc = Arrays.copyOfRange(lines, 1, lines.length - 3);

                availableLocations.add(availableLoc);
                switch (name) {
                    case "Chodba":
                        locations.add(new Hallway(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "Jidelna":
                        locations.add(new Cafeteria(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "Trida7":
                        locations.add(new Classroom7(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "Trida28":
                        locations.add(new Classroom28(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "Kabinet666":
                        locations.add(new Cabinet666(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "Kotelna":
                        locations.add(new BoilerRoom(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "WC":
                        locations.add(new Restrooms(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "Sborovna":
                        locations.add(new StaffRoom(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "Dilna":
                        locations.add(new Workshop(name, availableLoc, closed, hasNpc, label));
                        break;
                    case "Dvur":
                        locations.add(new Courtyard(name, availableLoc, closed, hasNpc, label));
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