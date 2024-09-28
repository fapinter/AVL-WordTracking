import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AVLTreeManager {
    private AVL avlTree;

    public AVLTreeManager() {
        this.avlTree = new AVL(); // Inicializa a árvore AVL vazia
    }

    public void readFileAndInsertWords(String filePath) throws IOException {
        File file = new File(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+"); // Extrai palavras usando regex para separar por não alfanuméricos
                for (String word : words) {
                    // Limpa a palavra de espaços e caracteres indesejados
                    word = word.trim().replaceAll("[^a-zA-Z0-9]", "");

                    // Verifica se a palavra não está vazia após a limpeza
                    if (!word.isEmpty()) {
                        // Insere a palavra diretamente na árvore AVL junto com o arquivo atual
                        avlTree.insertElement(word, file.getName()); // Passa a palavra e o nome do arquivo
                    }
                }
            }
        }
    }
}
