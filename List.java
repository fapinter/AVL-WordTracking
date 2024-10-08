import java.io.File;

public class List {
    private ListNode head;
    private ListNode tail;

    public List(){
        this.head = null;
        this.tail = null;
    }

    //Used to insert a new node into the list
    //always inserting at the end of the list
    public void insertNode(String fileName){
        ListNode newNode = new ListNode(fileName);

        if(this.tail == null){
            this.head = newNode;
        }
        else{
            this.tail.setNext(newNode);
        }
        this.tail = newNode;
    }
    //Searches for the node with the name of the file
    //if the node is not found, then creates a new node
    //and inserts in the end of the list
    public void addNodeFrequency(String fileName){
        ListNode curr = head;
        while(curr != null){
            //Node found
            if(curr.getFileName().equals(fileName)){
                curr.increaseFrequency();
                return;
            }
            if(curr.getNext() == null){
                insertNode(fileName);
                return;
            }
            curr = curr.getNext();
        }
        insertNode(fileName);
    }
    //Gets the length of the List
    public int length(){
        ListNode curr = head;
        int counter = 0;
        while(curr != null){
            counter++;
            curr = curr.getNext();
        }
        return counter;
    }
    //Prints all the occurrences of the
    //word in each file and the total frequency
    public void print() {
        ListNode curr = head;
        int totalFrequency = 0;
        // Traverse the list until the end
        while (curr != null) {
            String fileName = new File(curr.getFileName()).getName();
            System.out.printf("File: %s | Frequency: %d\n", fileName, curr.getFrequency());
            totalFrequency += curr.getFrequency();
            curr = curr.getNext();
        }
        System.out.println("Total occurrences: " + totalFrequency);
    }

}
