package command;

import command.console.Console;
import entity.Npc;
import entity.TypeOfNpc;
import entity.items.Bottle;
import entity.items.Item;
import entity.items.Medkit;
import entity.items.Tools;
import player.Player;
import world.Location;

public class Talk implements Command {
    private Console console;

    public Talk(Console console) {
        this.console = console;
    }

    /**
     * If player chose to talk, it will return him a message based on type of the npc.
     * @return
     */
    @Override
    public String execute() {
        Player player = console.getPlayer();
        Location currentLocation = player.getCurrentLocation();
        Npc npc = currentLocation.getNpc();

        if(currentLocation.getNpc() == null){
            return "Nikdo tu neni\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
        }else {
            TypeOfNpc typeOfNpc = npc.getTypeOfNpc();
            switch (typeOfNpc) {
                case AGGRESSIVE:
                    if(npc.isAlive()) {
                        return "Nechci s tebou mluvit, ukaz co v tobe je.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                    }else {
                        return "Tato entita uz je mrtva\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                    }
                case FRIENDLY:
                    if (npc.getName().equalsIgnoreCase("Schovany student")) {
                        Medkit medkit = null;
                        for (Item item : npc.getItems()) {
                            if (item instanceof Medkit) {
                                medkit = (Medkit) item;
                                break;
                            }
                        }

                        if (medkit != null) {
                            if (player.getInventory().addItem(medkit)) {
                                npc.removeItem(medkit);
                                return "Ahoj, takze ty jsi ten, ktery nas tu zachrani?\n" +
                                        "Pokud ano rad bych ti pomohl, tady mas lekarnicku.\n" +
                                        "Muzes ji pouzit kdykoliv.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                            } else {
                                return "Nemas misto na lekarnicku\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                            }

                        } else {
                            return "Nemam u sebe zadnou lekarnicku\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                        }
                    }

                    if (npc.getName().equalsIgnoreCase("Stary profesor")) {
                        System.out.println("Ahoj, nevedel jsem, ze sem nekdo prijde.\n" +
                                "Mam pro tebe pomucku, ale budes muset neco udelat.\n" +
                                "V ucebne 7 neco je, pokud to zabijes, dostanes ode me odmenu.");

                        Location classroom7 = console.getGameMap().getLocationByName("Trida7");
                        if (classroom7 != null) {
                            Npc mutant = classroom7.getNpc();
                            if (mutant != null && mutant.getName().equalsIgnoreCase("Mutantni student") && mutant.isAlive()) {
                                return "Musis prvne porazit to monstrum.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                            } else {
                                Bottle bottle = null;
                                for (Item item : npc.getItems()) {
                                    if (item instanceof Bottle) {
                                        bottle = (Bottle) item;
                                        break;
                                    }
                                }
                                if (bottle != null) {
                                    if (player.getInventory().addItem(bottle)) {
                                        npc.removeItem(bottle);
                                        return "Uz jsi ho zabil?\nTady mas. Kdyz si to das, tak budes vice odolny vuci utokum.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                                    } else {
                                        return "Nemas misto na lahvicku\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                                    }

                                } else {
                                    return "Promin, tu lahvicku u sebe nemam.\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                                }
                            }
                        }
                    }
                    if (npc.getName().equalsIgnoreCase("Mistr")){
                        Tools tools = null;
                        for (Item item : npc.getItems()) {
                            if (item instanceof Tools) {
                                tools = (Tools) item;
                                break;
                            }
                        }
                        if (npc.getItems().contains(tools)) {
                            return "Co po mne jeste chces, sekeru jsem ti uz opravil\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                        }
                        return "Pokud mi doneses sekeru, tak ti ji muzu opravit\n─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
                    }
            }
        }
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
