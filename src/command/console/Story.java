package command.console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Story {

    public void printStory() {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader("story.csv"))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

    }catch (FileNotFoundException e ){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

