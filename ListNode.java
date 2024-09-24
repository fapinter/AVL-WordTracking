public class ListNode {
    private String fileName;
    private int frequency;
    private ListNode next;

    //Node contains the name of the file where the word is found
    //and how many times the word was found in that file
    public ListNode(String fileName){
        this.fileName = fileName;
        this.frequency = 1;
        this.next = null;
    }

    public String getFileName() {
        return fileName;
    }

    public int getFrequency() {
        return frequency;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
    public void increaseFrequency(){
        this.frequency +=1;
    }
}
