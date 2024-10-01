public class AVL {
    private AVLNode root;

    // Constructor instancing the root as null
    // the first word inserted will be the root
    public AVL() {
        this.root = null;
    }

    public AVLNode getRoot() {
        return this.root;
    }

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
        }
        else if (res < 0) {
            node.setRight(insertRecursively(node.getRight(), word, currentFile));
        }
        else {
            node.increaseFrequency(currentFile); // Word already exists, increase frequency
            return node;
        }

        // Rebalance the node
        return rebalance(node);
    }

    // mode parameters sets if the element will be shown
    // 0: just the search
    // 1: search and print of the values
    public AVLNode elementExists(AVLNode node, String word, int mode) {
        AVLNode curr = node;
        AVLNode nodeWord = null;
        while (curr != null) {
            int res = curr.getWord().compareToIgnoreCase(word);
            if (res == 0) {
                if (mode == 1) curr.print();
                nodeWord = curr;
            }
            curr = (res > 0) ? curr.getLeft() : curr.getRight();
        }
        return nodeWord;
    }

    public AVLNode findFather(AVLNode son) {
        AVLNode father = getRoot();
        AVLNode returnFather = null;
        // Both father and son are the root
        if (father == son) {
            return returnFather;
        }
        while (father != null) {
            if (father.getLeft() == son || father.getRight() == son) {
                returnFather = father;
            }
            father = (father.getWord().compareTo(son.getWord()) > 0) ? father.getLeft() : father.getRight();
        }
        return returnFather;
    }

    public void removeElement(String word) {
        if(elementExists(getRoot(), word, 0) != null){
            System.out.println("Element not in the Tree");
            return;
        }
        this.root = removeBalanced(this.root, word);
    }

    private AVLNode removeBalanced(AVLNode node, String word) {

        AVLNode lowestNode, highestNode, extremesFather;

        int res = node.getWord().compareToIgnoreCase(word);

        // Left case
        if (res > 0) {
            node.setLeft(removeBalanced(node.getLeft(), word));
        }
        // Right case
        else if (res < 0) {
            node.setRight(removeBalanced(node.getRight(), word));
        }
        // Node found
        else {
            // Case 0.0: Node == root
            if (node == this.root) {
                // No children
                if (node.getLeft() == null && node.getRight() == null) {
                    this.root = null;
                }
                // Left child only
                else if (node.getLeft() != null && node.getRight() == null) {
                    highestNode = HighestElement(node.getLeft());
                    extremesFather = findFather(highestNode);
                    if (extremesFather != this.root) {
                        extremesFather.setRight(null);
                        if (highestNode.getLeft() != null) {
                            extremesFather.setRight(highestNode.getLeft());
                        }
                        highestNode.setLeft(this.root.getLeft());
                    }
                    this.root.setLeft(null);
                    this.root = highestNode;
                }
                // Right child only
                else if (node.getLeft() == null && node.getRight() != null) {
                    lowestNode = LowestElement(node.getRight());
                    extremesFather = findFather(lowestNode);
                    if (extremesFather != this.root) {
                        extremesFather.setLeft(null);
                        if (lowestNode.getRight() != null) {
                            extremesFather.setLeft(lowestNode.getRight());
                        }
                        lowestNode.setRight(this.root.getRight());
                    }
                    this.root.setRight(null);
                    this.root = lowestNode;
                }
                // Two children
                else {
                    lowestNode = LowestElement(node.getRight());
                    extremesFather = findFather(lowestNode);
                    if (extremesFather == this.root) {
                        lowestNode.setLeft(this.root.getLeft());
                    }
                    else {
                        extremesFather.setLeft(null);
                        if (lowestNode.getRight() != null) {
                            extremesFather.setLeft(lowestNode.getRight());
                        }
                        lowestNode.setRight(this.root.getRight());
                        lowestNode.setLeft(this.root.getLeft());
                        this.root.setLeft(null);
                    }
                    this.root.setRight(null);
                    this.root = lowestNode;
                }
            }
            // Case 1.0: Node != root
            else {
                AVLNode father = findFather(node);
                // No children
                if (node.getLeft() == null && node.getRight() == null) {
                    if (father.getLeft() == node) father.setLeft(null);
                    else father.setRight(null);
                }
                // Left child only
                else if (node.getLeft() != null && node.getRight() == null) {
                    highestNode = HighestElement(node.getLeft());
                    extremesFather = findFather(highestNode);
                    if (highestNode.getLeft() != null) {
                        extremesFather.setRight(highestNode.getLeft());
                    }
                    if (node.getLeft() != highestNode) {
                        highestNode.setLeft(node.getLeft());
                    }
                    if (father.getLeft() == node) father.setLeft(highestNode);
                    else father.setRight(highestNode);
                }
                // Right child only
                else if (node.getLeft() == null && node.getRight() != null) {
                    lowestNode = LowestElement(node.getRight());
                    extremesFather = findFather(lowestNode);
                    if (lowestNode.getRight() != null) {
                        extremesFather.setLeft(lowestNode.getRight());
                    }
                    if (node.getRight() != lowestNode) {
                        lowestNode.setRight(node.getRight());
                    }
                    if (father.getLeft() == node) father.setLeft(lowestNode);
                    else father.setRight(lowestNode);
                }
                // Two children
                else {
                    lowestNode = LowestElement(node.getRight());
                    extremesFather = findFather(lowestNode);
                    if (extremesFather != node) {
                        if (lowestNode.getRight() != null) {
                            extremesFather.setLeft(lowestNode.getRight());
                        }
                        if (node.getRight() != lowestNode) {
                            lowestNode.setRight(node.getRight());
                        }
                    }
                    lowestNode.setLeft(node.getLeft());
                    if (father.getLeft() == node) father.setLeft(lowestNode);
                    else father.setRight(lowestNode);
                }
            }

        }// Rebalance the node
        return rebalance(node);
    }

    public AVLNode HighestElement(AVLNode node) {
        AVLNode curr = node;
        AVLNode highestNode = null;
        while (curr != null) {
            highestNode = curr;
            curr = curr.getRight();
        }
        return highestNode;
    }

    public AVLNode LowestElement(AVLNode node) {
        AVLNode curr = node;
        AVLNode lowestNode = null;
        while (curr != null) {
            lowestNode = curr;
            curr = curr.getLeft();
        }
        return lowestNode;
    }

    public int height(AVLNode node) {
        if (node == null) return -1;
        int hLeft = height(node.getLeft());
        int hRight = height(node.getRight());
        return Math.max(hLeft, hRight) + 1;
    }

    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.getLeft();
        AVLNode T2 = x.getRight();
        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);
        return x; // Return new root
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.getRight();
        AVLNode T2 = y.getLeft();
        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);
        return y; // Return new root
    }

    private AVLNode rebalance(AVLNode node) {
        int balance = getBalance(node);
        // Left Case
        if (balance > 1 && getBalance(node.getLeft()) >= 0) {
            return rotateRight(node);
        }
        //Right Case
        if (balance < -1 && getBalance(node.getRight()) <= 0) {
            return rotateLeft(node);
        }
        // Left Right Case
        if (balance > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }
        // Right Left Case
        if (balance < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }
        return node;
    }
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