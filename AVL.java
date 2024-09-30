public class AVL {
    private AVLNode root;

    // Constructor instancing the root as null
    public AVL() {
        this.root = null;
    }

    public AVLNode getRoot() {
        return this.root;
    }

    // Insert method with dynamic height calculation and AVL rebalancing
    public void insertElement(String word, String currentFile) {
        this.root = insertRecursively(this.root, word, currentFile);
    }

    private AVLNode insertRecursively(AVLNode node, String word, String currentFile) {
        if (node == null) {
            return new AVLNode(word, currentFile);
        }
        int res = node.getWord().compareToIgnoreCase(word);

        if (res > 0) {
            node.setLeft(insertRecursively(node.getLeft(), word, currentFile));
        } else if (res < 0) {
            node.setRight(insertRecursively(node.getRight(), word, currentFile));
        } else {
            node.increaseFrequency(currentFile); // Word already exists, increase frequency
            return node;
        }

        // Rebalance the node after insertion
        return rebalance(node);
    }

    // Search for the element
    public AVLNode elementExists(AVLNode node, String word, int mode) {
        AVLNode curr = node;
        while (curr != null) {
            int res = curr.getWord().compareToIgnoreCase(word);
            if (res == 0) {
                if (mode == 1) curr.print();
                return curr;
            }
            curr = (res > 0) ? curr.getLeft() : curr.getRight();
        }
        return null;
    }

    // Remove element
    public void removeElement(String word) {
        this.root = removeRecursively(this.root, word);
    }

    private AVLNode removeRecursively(AVLNode node, String word) {
        if (node == null) return null;

        int res = node.getWord().compareToIgnoreCase(word);

        if (res > 0) {
            node.setLeft(removeRecursively(node.getLeft(), word));
        } else if (res < 0) {
            node.setRight(removeRecursively(node.getRight(), word));
        } else {
            // Node to be deleted found
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            // Node with two children: Get the smallest in the right subtree
            AVLNode temp = LowestElement(node.getRight());
            node.setWord(temp.getWord());
            node.setRight(removeRecursively(node.getRight(), temp.getWord()));
        }

        // Rebalance the node after deletion
        return rebalance(node);
    }

    // Helper to find the lowest value in the tree
    public AVLNode LowestElement(AVLNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    // AVL-specific balancing helpers

    // Calculate height dynamically
    public int height(AVLNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    // Get balance factor of node
    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    // Rebalance node based on balance factor
    private AVLNode rebalance(AVLNode node) {
        int balance = getBalance(node);

        // Left heavy
        if (balance > 1) {
            if (getBalance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }

        // Right heavy
        if (balance < -1) {
            if (getBalance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }

        return node;
    }

    // Right rotation
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.getLeft();
        AVLNode T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Return new root
        return x;
    }

    // Left rotation
    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.getRight();
        AVLNode T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        // Return new root
        return y;
    }

    // Print methods for tree traversal
    public void printPreOrder(AVLNode node) {
        if (node != null) {
            node.print();
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }

    public void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.getLeft());
            node.print();
            printInOrder(node.getRight());
        }
    }

    public void printPostOrder(AVLNode node) {
        if (node != null) {
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            node.print();
        }
    }
}
