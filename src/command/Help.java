package command;

public class Help implements Command {
    @Override
    public String execute() {
        return "Vsechny prikazy: jdi, zautoc, seber, vyhod, odemkni, prozkoumej, podej, pouzij, promluv si, napoveda, prikazy, konec";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
