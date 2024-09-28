import java.util.Scanner;

public class Menu {
    private AVL avlTree;

    public Menu(AVL avlTree) {
        this.avlTree = avlTree;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Print Pre-Order");
            System.out.println("2. Print In-Order");
            System.out.println("3. Print Post-Order");
            System.out.println("4. Search for a word");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Pre-Order Traversal:");
                    avlTree.printPreOrder(avlTree.getRoot());
                    System.out.println("==============================");
                    break;
                case 2:
                    System.out.println("In-Order Traversal:");
                    avlTree.printInOrder(avlTree.getRoot());
                    System.out.println("==============================");
                    break;
                case 3:
                    System.out.println("Post-Order Traversal:");
                    avlTree.printPostOrder(avlTree.getRoot());
                    System.out.println("==============================");
                    break;
                case 4:
                    System.out.print("Enter the word to search: ");
                    String word = scanner.nextLine();
                    if (avlTree.elementExists(avlTree.getRoot(), word, 1) != null) {
                        System.out.println("Word found.");
                    } else {
                        System.out.println("Word not found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
