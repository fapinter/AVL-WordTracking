import java.util.ArrayList;
//Missing methods: height(AVLNode node)
//Not finished methods: removeElement()

//AVL concepts not applied yet

public class AVL {
    private AVLNode root;

    public AVL(String word, int numNodes, ArrayList<String> fileNames, String currentFile){
        this.root = new AVLNode(word, numNodes, fileNames, currentFile);
    }
    public AVLNode getRoot(){
        return this.root;
    }
    public void insertElement(String word,int numNodes, ArrayList<String> fileNames, String currentFile){
        AVLNode curr = getRoot();
        AVLNode prev = null;
        boolean inserted = false;
        while(!inserted){
            if(curr!= null){

                int res = curr.getWord().compareTo(word);
                //Current word comes before the new Word
                if(res < 0){
                    prev = curr;
                    curr = curr.getRight();
                }
                //They are the same word
                else if(res == 0){
                    curr.increaseFrequency(currentFile);
                    inserted = true;
                }
                //Current word comes after the new Word
                else{
                    prev = curr;
                    curr = curr.getLeft();
                }
            }
            else{
                AVLNode newNode = new AVLNode(word, numNodes, fileNames, currentFile);
                //Previous word comes before the new Word
                if(prev.getWord().compareTo(word) < 0){
                    prev.setRight(newNode);
                    inserted = true;
                }
                else{
                    prev.setRight(newNode);
                    inserted = true;
                }
            }
        }
    }
    //PreOrdem to the search
    public AVLNode elementExists(AVLNode node,String word){
        AVLNode nodeWord = null;
        if(node != null){
            if(word.compareTo(node.getWord()) == 0){
                System.out.println("Element Found");
                node.print();
                nodeWord = node;
            }
            else{
                elementExists(node.getLeft(), word);
                elementExists(node.getRight(), word);
            }
        }
        System.out.println("Element not found");
        return nodeWord;
    }
    public AVLNode findFather(AVLNode father, AVLNode son){
        AVLNode returnFather = null;
        if(father != null){
            if(father.getLeft() == son || father.getRight() == son){
                returnFather = father;
            }
            else{
                findFather(father.getLeft(), son);
                findFather(father.getRight(), son);
            }
        }
        return returnFather;
    }
    //Not finished and this will take a long time to figure out
    public void removeElement(String word){
        //Implement after the search method
        AVLNode node= elementExists(getRoot(), word);
        AVLNode father = findFather(getRoot(), node);
        if(node != null){
            //Case 1: node has no descendants
            if(node.getLeft() == null && node.getRight() == null){
                if(father.getLeft() == node){
                    father.setLeft(null);
                }
                else{
                    father.setRight(null);
                }
            }
            //Case 2: node has a left descendant
            //Solution: Get the highestElement from the Left subTree
            else if(node.getLeft() != null && node.getRight() == null){

            }
            //Case 3: node has a right descendant
            //Solution: just get the right Node, because it will be bigger than the father anyway
            else if(node.getLeft() == null && node.getRight() != null){
                AVLNode aux = node.getRight();
                node.setRight(null);
                father.setRight(aux);
            }
            //Case 4: node has left and right descendants
            //Solution: get the highestElement from the left subTree, or the LowestElement from the right subTree
            else{

            }
        }
    }
    public void printPreOrder(AVLNode node){
        if(node != null){
            System.out.println(node.getWord());
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }
    public void printInOrder(AVLNode node){
        if(node != null){
            printInOrder(node.getLeft());
            System.out.println(node.getWord());
            printInOrder(node.getRight());
        }
    }
    public void printPostOrder(AVLNode node){
        if(node != null){
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            System.out.println(node.getWord());
        }
    }
}
