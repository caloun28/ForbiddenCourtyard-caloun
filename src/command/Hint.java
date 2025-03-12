package command;

import command.console.Console;
import player.Player;
import world.Location;

public class Hint implements Command {
    private Console console;

    public Hint(Console console) {
        this.console = console;
    }

    @Override
    public String execute() {
        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        switch (currentLocation.getName()){
            case "Chodba":
                return currentLocation.getLabel();
            case "Jidelna":
                return currentLocation.getLabel();
            case "Kabinet":
                return currentLocation.getLabel();
            case "Trida7":
                return currentLocation.getLabel();
            case "Trida28":
                return currentLocation.getLabel();
            case "Kotelna":
                return currentLocation.getLabel();
            case "Dilna":
                return currentLocation.getLabel();
            case "WC":
                return currentLocation.getLabel();
            case "Sborovna":
                return currentLocation.getLabel();
            case "Dvur":
                return currentLocation.getLabel();
            default:
                return "Spatna mistnost";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
