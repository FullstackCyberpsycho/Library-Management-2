/* org.example.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AuthorId {
    private File author_idF = new File("src/main/resources/author_id.txt");
    private int id = 0;

    public AuthorId() {
        initializeId();
    }

    private void initializeId() {
        try {
            if (!author_idF.exists()) {
                author_idF.createNewFile();
                id = 0;
                saveId();
            } else {
                Scanner myReader = new Scanner(author_idF);
                if (myReader.hasNextLine()) {
                    id = myReader.nextInt();
                }
                myReader.close();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при инициализации ID: " + e.getMessage());
        }
    }

    public void setId() {
        id++;
        saveId();
    }

    private void saveId() {
        try (FileWriter fileWriter = new FileWriter(author_idF, false)) {
            fileWriter.write(String.valueOf(id));
        } catch (IOException e) {
            System.out.println("Ошибка при записи ID: " + e.getMessage());
        }
    }

    public int getId() {
        return id;
    }
}*/