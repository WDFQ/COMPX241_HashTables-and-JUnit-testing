import java.util.ArrayList;

public class StrHashTable {
    private Node[] nodeArray = new Node[9];

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
            //if not at the chunk size limit yet, append to the chunked string
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
        return summedNums % nodeArray.length;
    }

    /**
     * Inserts a v into hash table
     * @param k the k for the node
     * @param v the v of the node
     */
    public void insert(String k, String v){
        //get index from hash function
        int index = hashFunction(k);

        //if the index is empty, create new node and put into array at index
        if(nodeArray[index] == null){
            Node newNode = new Node(k, v);
            nodeArray[index] = newNode;
        }
        else{
            System.out.println("Err: Collision at index " + index + ", value will not be inserted");
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
        if(nodeArray[index] != null){
            nodeArray[index] = null;
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

        //if it contains something at index, return true
        if(nodeArray[index] != null){
            return true;
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

        //if it contains the item, return its v
        if(nodeArray[index] != null){
            return nodeArray[index].getValue();
        }
        else{
            System.out.println("key does not exist in table");
            return null;
        }
    }

    /**
     * checks if table is empty
     * @param k k passed in 
     * @return returns if its true or false
     */
    public boolean isEmpty(){

        //loop through nodeArray to check if every v is empty
        for (int i = 0; i < nodeArray.length; i++) {
            //add to counter for every null v
            if(nodeArray[i] != null){
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
        //loop through nodeArray to check if each v is empty or not
        for (int i = 0; i < nodeArray.length; i++) {
            //add to counter for every non null v
            if(nodeArray[i] != null){
                counter++;
            }
        }   

        return counter;
    }

    /**
     * prints all values 
     */
    public void dump(){
        //check if table is empty first, if so, exit function
        if(isEmpty()){
            System.out.println("Emtpy Table");
            return;
        }

        //loop thorugh array and print each v
        for (int i = 0; i < nodeArray.length; i++) {
            if(nodeArray[i] != null){
                System.out.println(i + ": " + nodeArray[i].getKey() + ", " + nodeArray[i].getValue());
            }
        }   
    }

    /**
     * Double the table size
     */
    public void rehash(){
        //calculate the new array size
        int newArraySize = nodeArray.length * 2;

        //store old array and reinitialise old array to be the new array with double the size
        Node[] oldNodeArray = nodeArray;
        nodeArray = new Node[newArraySize];

        //loop thorugh old array
        for (Node node : oldNodeArray) {
            if(node != null){
                //get new index for each k in old array and store it in new array
                int newIndex = hashFunction(node.getKey());
                nodeArray[newIndex] = node;
            }
        }
    }


}
