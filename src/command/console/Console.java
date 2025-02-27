package command.console;

import command.Command;
import command.Exit;
import command.Movement;
import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private boolean exit = false;
    private HashMap<String, Command> map = new HashMap();
    private Scanner scanner = new Scanner(System.in);



    public void inicialization() {
        map.put("jdi", new Movement());
        map.put("exit", new Exit());
    }

    private String doComm() {
        String command = scanner.nextLine();
        command = command.trim();
        command = command.toLowerCase();
        if(map.containsKey(command)){

            return map.get(command).execute();
        }else{
            return "Nedefinovany command";
        }
    }

    public void start() {
        inicialization();
        try {
            do {
                doComm();
            } while(!exit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

