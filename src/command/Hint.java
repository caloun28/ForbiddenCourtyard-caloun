package command;

import command.console.Console;
import player.Player;
import world.Location;

/**
 * This class manages hints for all locations.
 */
public class Hint implements Command {
    private Console console;

    public Hint(Console console) {
        this.console = console;
    }

    /**
     * This method writes a hint for each room.
     * @return It returns an output for the given situation.
     */
    @Override
    public String execute() {

        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();

        switch (currentLocation.getName()){
            case "Chodba":
                return currentLocation.getLabel() + "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "Jidelna":
                return currentLocation.getLabel() + "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "Kabinet":
                return currentLocation.getLabel()+ "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "Trida7":
                return currentLocation.getLabel()+ "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "Trida28":
                return currentLocation.getLabel()+ "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "Kotelna":
                return currentLocation.getLabel()+ "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "Dilna":
                return currentLocation.getLabel()+ "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "WC":
                return currentLocation.getLabel()+ "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "Sborovna":
                return currentLocation.getLabel()+ "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            case "Dvur":
                return currentLocation.getLabel()+ "\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ";
            default:
                return "Spatna mistnost";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
