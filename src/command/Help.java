package command;

import java.io.*;

public class Help implements Command {
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
