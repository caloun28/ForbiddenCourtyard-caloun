package command;

public class Exit implements Command {
    public String execute() {
        return "The end";
    }

    public boolean exit() {
        return true;
    }
}
