//First Creating the Binary-Tree without the rotations, those will be added further into the project

public class AVLNode {
    private String word;
    private List frequencyCounter;
    private AVLNode left;
    private AVLNode right;

    public AVLNode(String word,String currentFile){
        this.word = word;
        this.frequencyCounter = new List();
        this.frequencyCounter.insertNode(currentFile);
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
        frequencyCounter.addNodeFrequency(fileName);
    }
    public void print(){
        System.out.println("Word: "+getWord());
        frequencyCounter.print();
    }
}
