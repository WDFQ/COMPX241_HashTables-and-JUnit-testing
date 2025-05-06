public class Main {
    public static void main(String[] args) {
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("1", "one");
        hashTable.insert("2", "two");
        hashTable.insert("3", "three");
        hashTable.insert("4", "four");

        //hashTable.delete("2");

        hashTable.insert("5", "five");
        hashTable.insert("6", "six");
        hashTable.insert("7", "seven");
        

        hashTable.insert("8", "eight");
        hashTable.insert("9", "nine");
        hashTable.insert("10", "ten");
        hashTable.insert("11", "eleven");
        hashTable.dump();
        System.out.println();
        System.out.println();

        System.out.println("no of elemnts in table: "+ hashTable.count());

        System.out.println();
        System.out.println();
        System.out.println("contains 11?" +   hashTable.contains("9"));
        System.out.println();
        System.out.println(hashTable.get("8"));
        System.out.println("is emtpy? " + hashTable.isEmpty());
      
    }
}
