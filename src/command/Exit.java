package command;

/**
 * Class for exiting the game.
 */
public class Exit implements Command {
    /**
     * When called, the game is ended.
     * @return Returns Konec.
     */
    public String execute() {
        return "Konec";
    }

    public boolean exit() {
        return true;
    }

}
