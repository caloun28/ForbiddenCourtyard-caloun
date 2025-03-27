package command;

public class Exit implements Command {
    /**
     * When called, the game is ended.
     * @return
     */
    public String execute() {
        return "Konec";
    }

    public boolean exit() {
        return true;
    }

}
