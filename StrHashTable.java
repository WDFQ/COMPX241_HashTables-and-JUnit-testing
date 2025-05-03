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

        
        //concatenate the numbers in each group
        //sum all number and mod by array size
    }
}
