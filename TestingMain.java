//File used only and specifically for testing the code
//do not alter the Main.java unless you are implementing
//the main for the final project

//Check with the others if they are using the TestingMain before
//Deleting its code and doing your own testing

public class TestingMain {
    public static void main(String[] args) {
        String str1 = "banana";
        String[] chars = {"cabra", "casa", "banana", "mamao"};
        String[] chars2 = {"CABRA", "camelo", "almondega", "vagem", "mamao"};
        AVL arvore = new AVL();
        for (String string : chars) {
            arvore.insertElement(string, "chars");
        }
        for (String string : chars2) {
            arvore.insertElement(string, "chars2");
        }

        arvore.printInOrder(arvore.getRoot());
        arvore.getRoot().print();
        arvore.elementExists(arvore.getRoot(), "mamao", 1);
        System.out.println(arvore.height(arvore.getRoot()));


    }
}
