import java.util.ArrayList;

public class StrHashTable {
    private Node[] nodeArray = new Node[9];

    /**
     * Returns the hash code (array index) 
     * @param key key
     * @return hash code
     */
    private int hashFunction(String key){
        //ascii array to hold all ascii values of each letter in key
        int[] asciiArray = new int[key.length()];

        //converts all characters in key to ascii value
        for (int i = 0; i < key.length(); i++) {
            //gets the char at i index in the key
            char c = key.charAt(i);
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
        int counter = 1;

        for (int ascii : asciiArray) {
            //if not at the chunk size limit yet, append to the chunked string
            if (counter % chunkSize == 0){
                //puts the ascii array in current index into the list
                chunkedString += asciiArray[ascii];
                //add chunked string into chunked String list and reset chunkedString variable
                chunkedStringList.add(chunkedString);
                chunkedString = "";
            }
            else{
                //append to chunked String
                chunkedString += asciiArray[ascii];
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
     * @param key the key for the node
     * @param value the value of the node
     */
    public void insert(String key, String value){
        //get index from hash function
        int index = hashFunction(key);

        //if the index is empty, create new node and put into array at index
        if(nodeArray[index] == null){
            Node newNode = new Node(key, value);
            nodeArray[index] = newNode;
        }
        else{
            System.out.println("Err: Collision at index " + index + ", value will not be inserted");
        }
    }

    /**
     * Deletes a value from a given key in the hashtable
     * @param key the key passed in
     */
    public void delete(String key){
        //get index from hash function
        int index = hashFunction(key);

        //if slot at index is not empty, make that slot equal to null
        if(nodeArray[index] != null){
            nodeArray[index] = null;
        }
        else{
            System.out.println("Key does not exist in table");
        }
    }

    /**
     * Finds if a value exists in the hash table
     * @param key key passed in
     * @return returns a bool
     */
    public boolean contains(String key){
        //get index from hash function
        int index = hashFunction(key);

        //if it contains something at index, return true
        if(nodeArray[index] != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Gets the value of the corresponding key
     * @param key key passed in
     * @return the value 
     */
    public String get(String key){
        //gets index from hash function
        int index = hashFunction(key);

        //if it contains the item, return its value
        if(nodeArray[index] != null){
            return nodeArray[index].getValue();
        }
        else{
            System.out.println("Key does not exist in table");
            return null;
        }
    }

    /**
     * checks if table is empty
     * @param key key passed in 
     * @return returns if its true or false
     */
    public boolean isEmpty(){

        //loop through nodeArray to check if every value is empty
        for (int i = 0; i < nodeArray.length; i++) {
            //add to counter for every null value
            if(nodeArray[i] != null){
                return true;
            }
        }   

       return false;
    }

    /**
     * gets the no of values in table
     * @return return number of values in table
     */
    public int count(){
        int counter = 0;
        //loop through nodeArray to check if each value is empty or not
        for (int i = 0; i < nodeArray.length; i++) {
            //add to counter for every non null value
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

        //loop thorugh array and print each value
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
                //get new index for each key in old array and store it in new array
                int newIndex = hashFunction(node.getKey());
                nodeArray[newIndex] = node;
            }
        }
    }


}
