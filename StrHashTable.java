import java.util.ArrayList;

public class StrHashTable {
    private Node[] nodeArray = new Node[10]; 
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
     * Inserts a value into hash table
     * @param k the key for the node
     * @param v the value of the node
     */
    public void insert(String k, String v){

        //get index from hash function
        int index = hashFunction(k);
        Node newNode = new Node(k, v);

        //if the index is empty, create new node and put into array at index
        if(nodeArray[index] == null){
            nodeArray[index] = newNode;
        }
        else{
            System.out.println("Err: Collision at index " + index + ", value will not be inserted");
        }

         //check to see if rehash is needed after new insert
         if (((double)this.count() / (double)nodeArray.length) >= LOAD_BALANCE) {
            rehash();
        }
    }

    /**
     * Deletes a value from a given key in the hashtable
     * @param k the key passed in
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
     * Finds if a value exists in the hash table
     * @param k key passed in
     * @return returns a bool
     */
    public boolean contains(String k){
        //get index from hash function
        int index = hashFunction(k);

        //if it contains something at index and keys match, return true
        if(nodeArray[index] != null && nodeArray[index].getKey().equals(k)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Gets the v of the corresponding k
     * @param k key passed in
     * @return the value
     */
    public String get(String k){

        //gets index from hash function
        int index = hashFunction(k);

        //if it contains the item, return its v
        if(nodeArray[index] != null && nodeArray[index].getKey().equals(k)){
            return nodeArray[index].getValue();
        }
        else{
            System.out.println("key does not exist in table");
            return "";
        }
    }

    /**
     * checks if table is empty
     * @param k key passed in 
     * @return returns if its true or false
     */
    public boolean isEmpty(){

        //loop through nodeArray to check if every v is empty
        for (int i = 0; i < nodeArray.length; i++) {
            //add to counter for every null value
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

        //loop thorugh array and print each v
        for (int i = 0; i < nodeArray.length; i++) {
            if(nodeArray[i] != null){
                System.out.println(i + ": " + nodeArray[i].getKey() + ", " + nodeArray[i].getValue());
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
