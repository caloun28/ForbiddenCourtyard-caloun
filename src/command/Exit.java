package command;

public class Exit implements Command {
    public String execute() {
        return "Konec";
    }

    public boolean exit() {
        return true;
    }

}
