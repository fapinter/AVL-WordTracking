import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.Normalizer;

//Palavras para teste da arvore
// sociedade, automacao, ecossistemas, Twitter, inteligência


public class Main {
    public static void main(String[] args) {
        String folderPath = "artigos";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        AVL avlTree = new AVL();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try{
                        Scanner scanner = new Scanner(file);
                        scanner.useDelimiter("\\s+");
                        while(scanner.hasNext()){
                            String word = scanner.next();
                            word = word.replaceAll("\\p{Punct}", "");
                            word = removerAcentos(word);
                            avlTree.insertElement(word, file.getAbsolutePath());
                        }
                    }
                    catch (FileNotFoundException exception){
                        exception.printStackTrace();
                    }
                }
            }
        }
        Menu menu = new Menu(avlTree);
        menu.display();
    }
    public static String removerAcentos(String palavra) {
        String normalized = Normalizer.normalize(palavra, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}
