public class Node{
    private String key;
    private String value;

    public Node(String key, String value){
        this.key = key;
        this.value = value;
    }

    /**
     * returns the key in this node
     * @return key in this node
     */
    public String getKey(){
        return this.key;
    }

    /**
     * returns the value in this node
     * @return value in this node
     */
    public String getValue(){
        return this.value;
    }

}
