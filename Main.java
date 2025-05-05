public class Main {
    public static void main(String[] args) {
        StrHashTable hashTable = new StrHashTable();

        hashTable.insert("1", "one");
        hashTable.insert("2", "two");
        hashTable.insert("3", "three");
        hashTable.insert("4", "four");

        hashTable.dump();

    }
}
