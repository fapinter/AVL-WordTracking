//File used only and specifically for testing the code
//do not alter the Main.java unless you are implementing
//the main for the final project

//Check with the others if they are using the TestingMain before
//Deleting its code and doing your own testing

public class TestingMain {
    public static void main(String[] args) {
        String str1 = "banana";
        String[] chars = {"cabra", "casa", "banana", "mamao"};
        String[] chars2 = {"cabra", "camelo", "almondega", "vagem"};
        AVL arvore = new AVL();
        for (int i = 0; i < chars.length; i++) {
            String string = chars[i];
            arvore.insertElement(string, "chars");
        }
        for (int i = 0; i < chars2.length; i++) {
            String string = chars2[i];
            arvore.insertElement(string, "chars2");
        }
        //arvore.removeElement("cabra");
        arvore.removeElement("casa");

        arvore.printInOrder(arvore.getRoot());
        //System.out.println(arvore.getRoot().getWord());


    }
}
