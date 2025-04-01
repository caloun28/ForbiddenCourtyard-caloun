package command;

import java.io.*;

/**
 * Class that writes out all commands that player can use.
 */
public class Help implements Command {
    /**
     * It will show every command that player can use.
     * @return It returns an output for the given situation.
     */
    @Override
    public String execute() {
        StringBuilder text = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("commands.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            return "Chyba při čtení souboru commands.csv: " + e.getMessage();
        }
        return text + "─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ─── ── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ── ─── ⋆⋅☆⋅⋆ ──";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
