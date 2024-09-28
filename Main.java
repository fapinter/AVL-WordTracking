import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {


        //String directoryPath = "";
        //List files = new List();

        String folderPath = "C:\\Users\\cryst\\Desktop\\Faculdade\\4 periodo\\Resolucao\\AVL-WordTracking\\artigos";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        AVL avlTree = new AVL();

        if (files != null) {
            for (File file : files) {
                // Ensure that the current item is a file
                if (file.isFile()) {
                    // Try-with-resources to ensure proper closure of resources
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        // Read each line of the file
                        while ((line = br.readLine()) != null) {
                            // Remove punctuation and whitespace, and split the line into words
                            String[] words = line.replaceAll("[^\\w\\s]", "").trim().split("\\s+"); // Regex to filter unwanted characters
                            for (String word : words) {
                                if (!word.isEmpty()) {
                                    avlTree.insertElement(word, file.getAbsolutePath()); // Pass the word and the current file path
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading the file: " + file.getName());
                    }
                }
            }
        }
        avlTree.printPreOrder(avlTree.getRoot());
    }
}