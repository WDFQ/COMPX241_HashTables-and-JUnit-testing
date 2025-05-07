public class Main {
    public static void main(String[] args) {
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("one", "1");
        hashTable.insert("two", "2");
        hashTable.insert("three", "3");
        hashTable.insert("four", "4");

        //hashTable.delete("2");

        hashTable.insert("five", "5");
        hashTable.insert("six", "6");
        hashTable.insert("seven", "7");
        

        hashTable.insert("eight", "8");
        hashTable.insert("nine", "9");
        hashTable.insert("ten", "10");
        hashTable.insert("eleven", "11");
        hashTable.insert("twelve", "12");

        hashTable.dump();
        System.out.println();
        System.out.println();

        System.out.println("no of elemnts in table: "+ hashTable.count());

        System.out.println();
        System.out.println();
        System.out.println("contains eleven?" +   hashTable.contains("eleven"));
        System.out.println();
        System.out.println(hashTable.get("eight"));
        System.out.println("is emtpy? " + hashTable.isEmpty());
      
    }
}
