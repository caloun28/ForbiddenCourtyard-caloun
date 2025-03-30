import command.console.Console;
public class Main {
    public static void main(String[] args) {
        try {
            Console c = new Console();
            c.start();
        }catch (Exception e){
            System.out.println("neco je spatne");
        }
    }
}