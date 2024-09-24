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
        boolean inserted = false;
        ListNode curr = head;
        while(!inserted){
            //Node found
            if(curr.getFileName().equals(fileName)){
                curr.increaseFrequency();
                inserted = true;
            }
            //Not the current node
            else{
                //If the curr is the tail
                if(curr.getNext() == null){
                    insertNode(fileName);
                    inserted = true;
                }
                curr = curr.getNext();

            }
        }
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
    public void print(){
        ListNode curr = head;
        int totalFrequency = 0;
        while(curr != null){
            System.out.print("Arquivo "+curr.getFileName()+" : ");
            System.out.println(curr.getFrequency());
            totalFrequency += curr.getFrequency();
            curr = curr.getNext();
        }
        System.out.println("Total de ocorrências : "+ totalFrequency);
    }
}
