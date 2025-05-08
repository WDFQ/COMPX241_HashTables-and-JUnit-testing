import java.util.ArrayList;
import java.util.LinkedList;

public class StrHashTableCollisions {
    private LinkedList<Node>[] linkedListArray = new LinkedList[10];

    private final double LOAD_BALANCE = 0.8;

    /**
     * Returns the hash code (array index) 
     * @param k key
     * @return hash code
     */
    private int hashFunction(String k){
        //ascii array to hold all ascii values of each letter in k
        int[] asciiArray = new int[k.length()];

        //converts all characters in k to ascii value
        for (int i = 0; i < k.length(); i++) {
            //gets the char at i index in the key
            char c = k.charAt(i);
            //casts char to integer
            int asciiValue = (int)c;
            //add ascii value to the array
            asciiArray[i] = asciiValue;
        }
        //group values to set size of s

        //split ascii array into chunks of 3
        int chunkSize = 3;

        ArrayList<String> chunkedStringList = new ArrayList<>();
        String chunkedString = "";
            
        for (int i = 0; i < asciiArray.length; i++) {
            chunkedString += asciiArray[i];
            //if not at the chunk size limit yet, append. Also add last value to regardless
            if ((i + 1) % chunkSize == 0 || i == asciiArray.length - 1){
                //add chunked string into chunked String list and reset chunkedString variable
                chunkedStringList.add(chunkedString);
                chunkedString = "";
            }
        }

        //sum all number and mod by array size
        int summedNums = 0;
        for (String string : chunkedStringList) {
            summedNums += Integer.parseInt(string);
        }

        //returns the index of the node
        return summedNums % linkedListArray.length;
    }

    /**
     * Inserts a v into hash table
     * @param k the k for the node
     * @param v the v of the node
     */
    public void insert(String k, String v){
        
        //get index from hash function
        int index = hashFunction(k);
        Node newNode = new Node(k, v);

        //if the index is empty, create new node and put into array at index
        if(linkedListArray[index] == null){ 
            
            linkedListArray[index] = new LinkedList<>();
            linkedListArray[index].add(newNode);
        }
        else{
            //check if it contains key
            if (!this.contains(k)) {
                linkedListArray[index].add(newNode);
            }
        }
        
        //check to see if rehash is needed
        if (((double)this.count() / (double)linkedListArray.length) >= LOAD_BALANCE) {
            rehash();
        }
       
    }

    /**
     * Deletes a v from a given k in the hashtable
     * @param k the k passed in
     */
    public void delete(String k){
        //get index from hash function
        int index = hashFunction(k);

        //if slot at index is not empty, make that slot equal to null
        if(linkedListArray[index] != null){
            if (linkedListArray[index].size() == 1) {
                linkedListArray[index] = null;
            }
            else{
                for(Node node: linkedListArray[index]){
                    if (node.getKey().equals(k)) {
                        linkedListArray[index].remove(node);
                    }
                }
            }
            
        }
        else{
            System.out.println("key does not exist in table");
        }
    }

    /**
     * Finds if a v exists in the hash table
     * @param k k passed in
     * @return returns a bool
     */
    public boolean contains(String k){
        //get index from hash function
        int index = hashFunction(k);

        if(linkedListArray[index] != null){
            
            //if the key exists inside the linked list at current index return true
            for (Node node : linkedListArray[index]) {
                if (node.getKey().equals(k)) {
                    return true;
                }
            }

            return false;
        }
        else{
            return false;
        }
    }
    
    /**
     * Gets the v of the corresponding k
     * @param k k passed in
     * @return the v 
     */
    public String get(String k){

        //gets index from hash function
        int index = hashFunction(k);

        //if it contains the key, loop through the linked list at current index to grab it
        if (this.contains(k)) {
            for (Node node : linkedListArray[index]) {
                if (node.getKey().equals(k)) {
                    return node.getValue();
                }
            }
        }

        System.out.println("key does not exist in table");
        return "";
    }

    /**
     * checks if table is empty
     * @param k k passed in 
     * @return returns if its true or false
     */
    public boolean isEmpty(){

        //loop through nodeArray to check if every v is empty
        for (int i = 0; i < linkedListArray.length; i++) {
            //add to counter for every null v
            if(linkedListArray[i] != null){
                return false;
            }
        }   

       return true;
    }

    /**
     * gets the no of values in table
     * @return return number of values in table
     */
    public int count(){
        int counter = 0;
        //loop through linkedListArray to check if each bucket is empty or not
        for (int i = 0; i < linkedListArray.length; i++) {
            //if bucket isnt null
            if (linkedListArray[i] != null) {
                //count  every entry in the bucket
                for (int j = 0; j < linkedListArray[i].size(); j++) {
                    counter++;
                }
            }            
        }   

        return counter;
    }

    /**
     * prints all values 
     */
    public void dump(){

        //loop thorugh array and print 
        for (int i = 0; i < linkedListArray.length; i++) {

            if(linkedListArray[i] != null){
                for (Node node : linkedListArray[i]) {
                    System.out.println(i + ": " + node.getKey() + ", " + node.getValue());
                }
            }
            else{
                System.out.println(i + ": " + "null");
            }
        }   
    }

    /**
     * Double the table size
     */
    public void rehash(){
        //calculate the new array size
        int newArraySize = linkedListArray.length * 2;

        //store old array and reinitialise old array to be the new array with double the size
        LinkedList<Node>[] oldLinkedListArray = linkedListArray;
        
        linkedListArray = new LinkedList[newArraySize];

        //loop thorugh old array
        for (int i = 0; i < oldLinkedListArray.length; i++) {
            if (oldLinkedListArray[i] != null) {
                for (Node node : oldLinkedListArray[i]) {

                    if(node != null){
                        //get new index for each k in old array and store it in new array
                        int newIndex = hashFunction(node.getKey());
                         //if the index is empty, create new node and put into array at index
    
                        if(linkedListArray[newIndex] == null){
                            
                            linkedListArray[newIndex] = new LinkedList<>();
                            
                        }

                        linkedListArray[newIndex].add(node);
                    }
                }
            }
            
        }
    }
}   
