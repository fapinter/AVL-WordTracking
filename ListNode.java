public class ListNode {
    private String file_name;
    private int frequency;
    private ListNode next;

    public ListNode(String file_name, ListNode node){
        this.file_name = file_name;
        this.frequency = 0;
        this.next = null;
        if(node != null){
            this.next = node;
        }
    }

    public String getFile_name() {
        return file_name;
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
