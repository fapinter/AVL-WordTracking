//File used only and specifically for testing the code
//do not alter the Main.java unless you are implementing
//the main for the final project

//Check with the others if they are using the TestingMain before
//Deleting its code and doing your own testing

import java.util.ArrayList;

public class TestingMain {
    public static void main(String[] args){
        String firstWord = "mamona";
        ArrayList<String> files = new ArrayList<String>();
        files.add("arquivo1.txt");
        files.add("arquivo2.txt");

        AVL arvore = new AVL(firstWord, files.size(), files, "arquivo1.txt");
        arvore.insertElement("mamona",files.size(), files, "arquivo1.txt");
        arvore.insertElement("mamona",files.size(), files, "arquivo1.txt");

        arvore.removeElement("mamona");
        arvore.insertElement("mansao",files.size(), files, "arquivo1.txt");
        arvore.getRoot().print();

    }
}
