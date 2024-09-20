//File used only and specifically for testing the code
//do not alter the Main.java unless you are implementing
//the main for the final project

//Check with the others if they are using the TestingMain before
//Deleting its code and doing your own testing

import java.util.ArrayList;

public class TestingMain {
    public static void main(String[] args){
        ArrayList<String> fileNames = new ArrayList<String>();
        fileNames.add("Arquivo1");
        fileNames.add("Arquivo2");
        fileNames.add("Arquivo3");
        int numNodes = fileNames.size();

        List lista = new List(numNodes, fileNames);
        System.out.println(lista.length());
        lista.print();
        lista.addNodeFrequency("Arquivo1");
        lista.addNodeFrequency("Arquivo1");
        lista.addNodeFrequency("Arquivo2");
        lista.print();
    }
}
