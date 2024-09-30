//AVL concepts not applied yet
public class AVL {
    private AVLNode root;

    //Constructor instancing the root as null
    //the first word inserted will be the root
    public AVL(){this.root = null;}
    public AVLNode getRoot(){
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
        //+, node.getWord comes after word
        if (res > 0) {
            node.setLeft(insertRecursively(node.getLeft(), word, currentFile));
        }
        //-, node.getWord comes before word
        else if (res < 0) {
            node.setRight(insertRecursively(node.getRight(), word, currentFile));
        }
        //node.getWord is the word
        else {
            node.increaseFrequency(currentFile);
        }
        return node;
    }
    //mode parameters sets if the element will be shown
    //0: just the search
    //1: search and print of the values
    public AVLNode elementExists(AVLNode node, String word, int mode) {
        AVLNode curr = node;
        AVLNode nodeWord=  null;
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

    public AVLNode findFather(AVLNode son){
        AVLNode father = getRoot();
        AVLNode returnFather = null;
        //Both father and son are the root
        if(father == son){return returnFather;}
        while(father != null){
            if(father.getLeft() == son || father.getRight() == son){
                returnFather = father;
            }
            father = (father.getWord().compareTo(son.getWord()) > 0) ? father.getLeft() : father.getRight();
        }
        return returnFather;
    }

    public void removeElement(String word){
        //May return a node or null
        AVLNode node = elementExists(getRoot(), word, 0);
        AVLNode lowestNode;
        AVLNode highestNode;
        AVLNode extremesFather;
        //Node found
        if(node != null){
            //Case 0.0: Node == root
            if(node == this.root){
                //No sons
                if(node.getLeft() == null && node.getRight() == null){
                    this.root = null;}
                //Left son
                else if(node.getLeft() != null && node.getRight() == null){
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
                //Right son
                else if(node.getLeft() == null && node.getRight() != null){
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
                //Both left and right son
                else{
                    lowestNode = LowestElement(node.getRight());
                    extremesFather = findFather(lowestNode);
                    if(extremesFather == this.root){
                        lowestNode.setLeft(this.root.getLeft());
                    }
                    else{
                        extremesFather.setLeft(null);
                        if(lowestNode.getRight() != null){
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
            //Case 1.0: Node != root
            else{
                AVLNode father = findFather(node);
                //No sons
                if(node.getLeft() == null && node.getRight() == null){
                    if(father.getLeft() == node) father.setLeft(null);
                    else father.setRight(null);
                }
                //Left son
                else if(node.getLeft() != null && node.getRight() == null){
                    highestNode = HighestElement(node.getLeft());
                    extremesFather = findFather(highestNode);

                    if(highestNode.getLeft() != null){
                        extremesFather.setRight(highestNode.getLeft());
                    }
                    if(node.getLeft() != highestNode){
                        highestNode.setLeft(node.getLeft());
                    }
                    if(father.getLeft() == node) father.setLeft(highestNode);
                    else father.setRight(highestNode);
                }
                //Right son
                else if(node.getLeft() == null && node.getRight() != null){
                    lowestNode = LowestElement(node.getRight());
                    extremesFather = findFather(lowestNode);
                    if(lowestNode.getRight() != null){
                        extremesFather.setLeft(lowestNode.getRight());
                    }
                    if(node.getRight() != lowestNode){
                        lowestNode.setRight(node.getRight());
                    }
                    if(father.getLeft() == node) father.setLeft(lowestNode);
                    else father.setRight(lowestNode);
                }
                //Both left and right son
                else{
                    lowestNode = LowestElement(node.getRight());
                    extremesFather = findFather(lowestNode);
                    if(extremesFather != node){
                        if(lowestNode.getRight() != null){
                            extremesFather.setLeft(lowestNode.getRight());
                        }
                        if(node.getRight() != lowestNode){
                            lowestNode.setRight(node.getRight());
                        }
                    }
                    lowestNode.setLeft(node.getLeft());
                    if(father.getLeft() == node) father.setLeft(lowestNode);
                    else father.setRight(lowestNode);
                }
            }
        }
        //Node not found
        else System.out.println("Element not in the Tree");
    }

    public AVLNode HighestElement(AVLNode node){
        AVLNode curr = node;
        AVLNode highestNode = null;
        while(curr != null){
            highestNode = curr;
            curr = curr.getRight();
        }
        return highestNode;
    }
    public AVLNode LowestElement(AVLNode node){
        AVLNode curr = node;
        AVLNode lowestNode = null;
        while(curr != null){
            lowestNode = curr;
            curr = curr.getLeft();
        }
        return lowestNode;
    }
    public int height(AVLNode node){
        if(node == null) return -1;

        int hLeft = height(node.getLeft());
        int hRight = height(node.getRight());

        if(hLeft > hRight) return hLeft +1;

        return hRight + 1;
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
