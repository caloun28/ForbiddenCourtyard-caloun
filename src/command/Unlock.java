package command;

import command.console.Console;
import entity.items.Item;
import entity.items.Key;
import entity.items.TypeOfKey;
import player.Player;
import world.Location;
import world.Map;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is for unlocking rooms.
 */
public class Unlock implements Command {
    private Console console;
    private Scanner sc = new Scanner(System.in);
    private Map map;

    public Unlock(Console console) {
        this.console = console;
        this.map = console.getGameMap();
    }

    /**
     * With this method, player can choose to unlock a locked door. He can unlock it, only if he has an assigned key
     * and access to the room.
     * @return It returns an output for the given situation.
     */
    @Override
    public String execute() {

        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        try {

            System.out.print("Zadej název místnosti, kterou chceš odemknout\n>> ");
            String input = sc.nextLine().trim().toLowerCase();

            Location toUnlock = null;

            for (Location location : map.getLocations()) {
                if (location.getName().equalsIgnoreCase(input)) {
                    toUnlock = location;
                    break;
                }
            }

            if (toUnlock == null) {
                return "Tato místnost neexistuje.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            }

            if (!toUnlock.isClosed()) {
                return "Tato místnost je již otevřená.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            }

            boolean canAccess = false;
            for (String availableLocation : currentLocation.getAvailableLocations()) {
                if (availableLocation.equalsIgnoreCase(toUnlock.getName())) {
                    canAccess = true;
                    break;
                }
            }

            if (!canAccess) {
                return "Nemůžeš odemknout místnost, ke které nemáš přístup.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            }



            TypeOfKey requiredKeyType;
            switch (toUnlock.getName().toLowerCase()) {
                case "dvur":
                    requiredKeyType = TypeOfKey.FOR_COURTYARD;
                    break;
                case "kabinet":
                    requiredKeyType = TypeOfKey.FOR_CABINET;
                    break;
                case "kotelna":
                    requiredKeyType = TypeOfKey.FOR_BOILERROOM;
                    break;
                default:
                    return "Tuto místnost nelze odemknout klicem.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
            }


            for (Item item : player.getInventory().getItems().values()) {
                if (item instanceof Key key) {

                    if (key.getKeyType() == requiredKeyType && !key.isUsed()) {

                        toUnlock.setClosed(false);
                        key.setUsed(true);

                        return "Pouzil jsi " + key.getName() + " a odemkl jsi " + toUnlock.getName() + ".\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                    }
                }
            }


        } catch (InputMismatchException e) {
            return "Chyba ve vstupu";
        }
        return player + "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";

    }

    @Override
    public boolean exit() {
        return false;
    }
}
