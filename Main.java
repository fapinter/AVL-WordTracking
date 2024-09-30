import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\cryst\\Desktop\\Faculdade\\4 periodo\\Resolucao\\AVL-WordTracking\\artigos";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        AVL avlTree = new AVL();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] words = line.replaceAll("[^\\w\\s]", "").trim().split("\\s+");
                            for (String word : words) {
                                if (!word.isEmpty()) {
                                    avlTree.insertElement(word, file.getAbsolutePath());
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading the file: " + file.getName());
                    }
                }
            }
        }

        Menu menu = new Menu(avlTree);
        menu.display();
    }
}
