import java.util.ArrayList;

//First Creating the Binary-Tree without the rotations, those will be added further into the project


public class AVLNode {
    private String word;
    private List frequency_counter;
    private AVLNode left;
    private AVLNode right;

    public AVLNode(String word, int numNodes, ArrayList<String> fileNames, String currentFile){
        this.word = word;
        this.frequency_counter = new List(numNodes, fileNames, currentFile);
        this.left = null;
        this.right = null;
    }

    public void setLeft(AVLNode node){
        this.left = node;
    }
    public void setRight(AVLNode node){
        this.right = node;
    }
    public AVLNode getLeft(){
        return this.left;
    }
    public AVLNode getRight(){
        return this.right;
    }
    public String getWord(){
        return this.word;
    }
    public void increaseFrequency(String fileName){
        frequency_counter.addNodeFrequency(fileName);
    }
}
