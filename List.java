import java.util.ArrayList;

public class List {
    ListNode head;

    public List(int numNodes, ArrayList<String> fileNames, String currentFile){
        this.head = null;
        ListNode prev = null;
        for(int i = 0; i < numNodes; i++){
            ListNode node = new ListNode(fileNames.get(i), null);
            if(prev != null){
                prev.setNext(node);
            }
            else{
                this.head = node;
            }
            prev = node;
        }
        addNodeFrequency(currentFile);
    }

    public void addNodeFrequency(String fileName){
        boolean inserted = false;
        ListNode curr = head;
        while(!inserted){
            if(curr.getFile_name().equals(fileName)){
                curr.increaseFrequency();
                inserted = true;
            }
            else{
                if(curr.getNext() == null){
                    System.out.println("ERRO: file node not found");
                }
                curr = curr.getNext();

            }
        }
    }
    public int length(){
        ListNode curr = head;
        int counter = 0;
        while(curr != null){
            counter++;
            curr = curr.getNext();
        }
        return counter;
    }
    public void print(){
        ListNode curr = head;
        while(curr != null){
            System.out.print(curr.getFile_name()+" ");
            System.out.println(curr.getFrequency());
            curr = curr.getNext();
        }
    }
}
