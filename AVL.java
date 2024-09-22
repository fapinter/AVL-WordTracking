import java.util.ArrayList;
//Missing methods: height(AVLNode node)

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
        if(this.root == null){
            this.root = new AVLNode(word, numNodes, fileNames, currentFile);
            return;
        }
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
    //PreOrder to the search
    public AVLNode elementExists(AVLNode node,String word, int mode){
        boolean found = false;
        AVLNode nodeWord = null;
        if(node != null){
            if(word.compareTo(node.getWord()) == 0){
                if(mode == 1){
                    node.print();
                }
                found = true;
                nodeWord = node;
            }
            else{
                elementExists(node.getLeft(), word, mode);
                elementExists(node.getRight(), word, mode);
            }
        }
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
        AVLNode node = elementExists(getRoot(), word, 0);
        AVLNode father = null;

        //isRoot is used to verify if the node we are removing is the root,
        //this verification is needed because otherwise the findFather method
        //will result in an Exception
        boolean isRoot = true;

        if(node != null){
            if(node != this.root){
                isRoot = false;
                father = findFather(getRoot(), node);
            }
            //Case 1: node has no descendants
            if(node.getLeft() == null && node.getRight() == null){
                if(isRoot){
                    this.root = null;
                }
                else{
                    if(father.getLeft() == node){
                        father.setLeft(null);
                    }
                    else{
                        father.setRight(null);
                    }
                }
            }
            //Case 2: node has only the left descendant
            //Solution: Get the highestElement from the Left subTree
            else if(node.getLeft() != null && node.getRight() == null){

                String highest = HighestElement(node.getLeft());
                AVLNode HighestNode = elementExists(node.getLeft(), highest, 0);
                AVLNode HighestNodeFather = findFather(father, HighestNode);

                if(HighestNode.getLeft() != null){
                    HighestNodeFather.setRight(HighestNode.getLeft());
                }
                if(node.getRight() != HighestNode){
                    HighestNode.setLeft(node.getLeft());
                }
                if(isRoot){
                    this.root.setLeft(null);
                    this.root = HighestNode;
                }
                else{
                    if(father.getLeft() == node){
                        father.setLeft(HighestNode);
                    }
                    else{
                        father.setRight(HighestNode);
                    }
                }
            }
            //Case 3: node has only the right descendant
            //Solution: Get the LowestElement from the Right subTree
            else if(node.getLeft() == null && node.getRight() != null){
                String lowest = LowestElement(node.getRight());
                AVLNode LowestNode = elementExists(node.getRight(), lowest, 0);

                //Maintenance on the LowestNode's father if necessary
                AVLNode LowestNodeFather = findFather(father, LowestNode);
                if(LowestNode.getRight() != null){
                    LowestNodeFather.setLeft(LowestNode.getRight());
                }
                if(node.getRight() != LowestNode){
                    LowestNode.setRight(node.getRight());
                }
                if(isRoot){
                    this.root.setRight(null);
                    this.root = LowestNode;
                }
                else{

                    if(father.getLeft() == node){
                        father.setLeft(LowestNode);
                    }
                    else{
                        father.setRight(LowestNode);
                    }
                }
            }
            //Case 4: node has both left and right descendants
            //Solution: get the LowestElement from the right subTree and set the left subTree as its left
            else{
                String lowest = LowestElement(node.getRight());
                //The lowest Node from the Right will never have a left Descendant and will always be higher than the
                //entire left subTree
                AVLNode LowestNode = elementExists(node.getRight(), lowest, 0);

                //Maintenance on the LowestNode's father if necessary
                AVLNode LowestNodeFather = findFather(father, LowestNode);
                if(LowestNode.getRight() != null){
                    LowestNodeFather.setLeft(LowestNode.getRight());
                }

                if(node.getRight() != LowestNode){
                    LowestNode.setRight(node.getRight());
                }
                if(node.getLeft() != LowestNode){
                    LowestNode.setLeft(node.getLeft());
                }
                if(isRoot){
                    this.root.setRight(null);
                    this.root.setLeft(null);
                    this.root = LowestNode;
                }

                else{
                    if(father.getLeft() == node){
                        father.setLeft(LowestNode);
                    }
                    else{
                        father.setRight(LowestNode);
                    }
                }

            }
        }
        else{
            System.out.println("Element not in the Tree");
        }
    }

    public String HighestElement(AVLNode node){
        String highest = "a";
        if(node != null){
            highest = node.getWord();
            String esq = HighestElement(node.getLeft());
            if(esq.compareTo(highest) > 0){
                highest = esq;
            }
            String dir = HighestElement(node.getRight());
            if(dir.compareTo(highest) > 0){
                highest = dir;
            }
        }
        return highest;
    }
    public String LowestElement(AVLNode node){
        String lowest = "zzzzzzzzzzzzzzzz";
        if(node != null){
            lowest = node.getWord();
            String esq = LowestElement(node.getLeft());
            if(esq.compareTo(lowest) < 0){
                lowest = esq;
            }
            String dir = LowestElement(node.getRight());
            if(dir.compareTo(lowest) < 0){
                lowest = dir;
            }
        }
        return lowest;
    }

    public void printPreOrder(AVLNode node){
        if(node != null){
            System.out.println(node.getWord());
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }
    public void printInOrder(AVLNode node){
        if(!rootExists()){
            return;
        }
        if(node != null){
            printInOrder(node.getLeft());
            System.out.println(node.getWord());
            printInOrder(node.getRight());
        }
    }
    public void printPostOrder(AVLNode node){
        if(!rootExists()){
            return;
        }
        if(node != null){
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            System.out.println(node.getWord());
        }
    }

    public int height(AVLNode node){
        int hLeft, hRight;
        if(node == null){
            return -1;
        }
        hLeft = height(node.getLeft());
        hRight = height(node.getRight());
        if(hLeft > hRight){
            return hLeft +1;
        }
        return hRight + 1;
    }
    public boolean rootExists(){
        boolean bool = true;
        if(this.root == null){
            bool = false;
        }
        return bool;
    }
}
