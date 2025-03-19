package command;

import command.console.Console;
import entity.items.Item;
import player.Player;
import world.Location;

public class LookAround implements Command {
    private Console console;

    public LookAround(Console console) {
        this.console = console;
    }

    @Override
    public String execute() {
        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();

        StringBuilder text = new StringBuilder();
        text.append("Jsi v: ").append(currentLocation.getName()).append("\n");

        if (currentLocation.getNpc() != null) {
            if(currentLocation.getNpc().isAlive()) {
                text.append("V mistnosti je : ").append(currentLocation.getNpc().getName()).append("\n");
            }else {
                text.append("V mistnosti je mrtvy/a: ").append(currentLocation.getNpc().getName()).append("\n");
            }
        } else {
            text.append("V mistnosti nidko neni.\n");
        }

        if (!currentLocation.getItems().isEmpty()) {
            text.append("V mistnosti jsou tyto predmety:");
            for (Item item : currentLocation.getItems().values()) {
                text.append(" - ").append(item.getName()).append("\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ");
            }
        } else {
            text.append("V mistnosti nejsou zadne predmety.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──");
        }

        return text.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
