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
    public void dumptest1(){
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
     * Testing delete() to see if the head of the bucket is deleted
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void testDeleteHeadOfBucket(){

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
     * Tests if delete() is deleting the last of the bucket linked list correctly
     */
    @Test
    @DisplayName("Test delete(), dependent on insert() and dump()")
    public void testDeleteTailOfBucket(){

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

    @Test
    @DisplayName("Test checking if table is empty")
    public void isEmptyTest1(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        Assertions.assertTrue(hashTable.isEmpty());;
    }

    @Test
    @DisplayName("Test checking if table is not empty, dependent on insert()")
    public void testCheckingNonEmptyTable(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();
        hashTable.insert("1", "one");

        Assertions.assertFalse(hashTable.isEmpty());;
    }

   
}
