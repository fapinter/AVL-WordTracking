import java.util.ArrayList;

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
}
