import org.junit.jupiter.api.*;

import java.io.*;


public class StrHashTableCollisionsTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }

    /**
     * Testing dump() when there are no values  
     */
    @Test
    @DisplayName("Test dump()")
    public void dumpTest1(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "0: null\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: null";
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Testing dump() when there is 1 value
     */
    @Test
    @DisplayName("Test dump(), dependent on insert()")
    public void dumpTest2(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();
        hashTable.insert("1", "one");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "0: null\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: 1, one";
        Assertions.assertEquals(expected, actual);
    }

    /**
     * testing insert() to see if 1 value gets inserted
     */
    @Test
    @DisplayName("Test insert(), dependent on dump()")
    public void insertTest1(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("one", "1");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected =  "0: null\r\n" + 
                        "1: one, 1\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: null";
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Testing insert() to see if 2 values gets inserted 
     */
    @Test
    @DisplayName("Testing insert(), dependent on dump()")
    public void insertTest2(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("1", "one");
        hashTable.insert("2", "two");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "0: 2, two\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: 1, one";
        Assertions.assertEquals(expected, actual);
    }   

    /**
     * Testing insert() to see if duplicate keys can be inserted
     */
    @Test
    @DisplayName("Test insert(), dependent on dump()")
    public void insertTest3(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("1", "one");
        hashTable.insert("1", "newOne");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "0: null\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" +  
                        "9: 1, one";
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Testing delete() to see if one entry gets deleted
     */
    @Test
    @DisplayName("Test delete(), dependent on dump() and insert()")
    public void deleteTest1(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("1", "one");
        hashTable.delete("1");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "0: null\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: null";
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Testing delete() for a key that doesnt exist
     */
    @Test
    @DisplayName("Test delete()")
    public void deleteTest2(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.delete("2");

        String actual = outputStreamCaptor.toString().trim();
        String expected = "key does not exist in table";
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Testing delete() on a key that exists within the same bucket as another key
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void deleteTest3(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();
        hashTable.insert("1", "one");
        hashTable.insert("11", "eleven");
        hashTable.delete("1");

        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "0: null\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: 11, eleven";

        Assertions.assertEquals(expected, actual);
    }

     /**
     * Testing delete() to see if the head of the bucket is deleted (bucket test)
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void deleteTest4(){

        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("2", "two");
        hashTable.insert("22", "twentytwo");
        hashTable.delete("22");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "0: 2, two\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: null";

        Assertions.assertEquals(expected, actual);
    }

     /**
     * Tests if delete() is deleting the last of the bucket linked list correctly (bucket test)
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void deleteTest5(){

        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("2", "two");
        hashTable.insert("22", "twentytwo");
        hashTable.delete("2");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = "0: 22, twentytwo\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: null";
         
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test get() to see if it can get an existing key
     */
    @Test
    @DisplayName("Test get(), dependent on insert()")
    public void getTest1(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();
        hashTable.insert("1", "one");

        String actual = hashTable.get("1");
        String expected = "one";

        Assertions.assertEquals(expected, actual);
    }

     /**
     * Test get() to see if it can get an non existing key
     */
    @Test
    @DisplayName("Test get()")
    public void getTest2(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        String actual = hashTable.get("1");
        String expected = "";

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test get() to see if it can get an existing key in a bucket with more than one value
     */
    @Test
    @DisplayName("Test get(), depend on insert(), check true")
    public void getTest3(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();
        hashTable.insert("1", "one");
        hashTable.insert("11", "eleven");

        String actual = hashTable.get("1");
        String expected = "one";
        

        Assertions.assertEquals(expected, actual);
    }



    /**
     * Test isEmpty() to see if it returns true on emtpy hashtable
     */
    @Test
    @DisplayName("Test isEmpty(), check true")
    public void isEmptyTest1(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        Assertions.assertTrue(hashTable.isEmpty());;
    }

    /**
     * Test isEmpty() to see if it returns false on non empty hashtable
     */
    @Test
    @DisplayName("Test isEmpty(), dependent on insert(), check false")
    public void isEmptyTest2(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();
        hashTable.insert("1", "one");

        Assertions.assertFalse(hashTable.isEmpty());;
    }

    /**
     * Test isEmpty() to see if it returns true on empty hashtable after deletion
     */
    @Test
    @DisplayName("Test isEmpty(), dependent on insert(), check true")
    public void isEmptyTest3(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();
        hashTable.insert("1", "one");
        hashTable.delete("1");

        Assertions.assertTrue(hashTable.isEmpty());;
    }

    /**
     * Test contains() to see if it returns true when searching for a value that exist
     */
    @Test
    @DisplayName("Test contains(), dependent on insert(), check true")
    public void containsTest1(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("1", "one");
       
        Assertions.assertTrue(hashTable.contains("1"));;
    }

       /**
     * Test contains() to see if it returns false when searching for a value that dont exist
     */
    @Test
    @DisplayName("Test contains(), check false")
    public void containsTest2(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();
       
        Assertions.assertFalse(hashTable.contains("1"));;
    }

    /**
     * Test count() on an empty table
     */
    @Test
    @DisplayName("Test count()")
    public void countTest1() {
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        int actual = hashTable.count(); 
        int expected = 0;

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test count() on table with one value
     */
    @Test
    @DisplayName("Test count(), depend on insert()")
    public void countTest2() {
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("1", "one");
        int actual = hashTable.count(); 
        int expected = 1;

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test count() on table with collision of two values
     */
    @Test
    @DisplayName("Test count(), depend on insert()")
    public void countTest3() {
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("1", "one");
        hashTable.insert("11", "eleven");
        int actual = hashTable.count(); 
        int expected = 2;

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests if rehash() runs when capacity reaches 80% or more
     */
    @Test
    @DisplayName("Test rehash(), dependent on insert()")
    public void rehashTest(){
        // Assign
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        // Act
        hashTable.insert("1", "one");
        hashTable.insert("2", "two");
        hashTable.insert("3", "three");
        hashTable.insert("4", "four");
        hashTable.insert("5", "five");
        hashTable.insert("6", "six");
        hashTable.insert("7", "seven");
        hashTable.insert("8", "eight");
        hashTable.insert("9", "nine");
        hashTable.dump();

        String actual = outputStreamCaptor.toString().trim();
        String expected = 
                        "0: null\r\n" + 
                        "1: null\r\n" + 
                        "2: null\r\n" + 
                        "3: null\r\n" + 
                        "4: null\r\n" + 
                        "5: null\r\n" + 
                        "6: null\r\n" + 
                        "7: null\r\n" + 
                        "8: null\r\n" + 
                        "9: 1, one\r\n" +
                        "10: 2, two\r\n" + 
                        "11: 3, three\r\n" +
                        "12: 4, four\r\n" +
                        "13: 5, five\r\n" +   
                        "14: 6, six\r\n" +
                        "15: 7, seven\r\n" +
                        "16: 8, eight\r\n" + 
                        "17: 9, nine\r\n" +   
                        "18: null\r\n" +
                        "19: null";

        // Assert
        Assertions.assertEquals(expected, actual);
    }




   
}
