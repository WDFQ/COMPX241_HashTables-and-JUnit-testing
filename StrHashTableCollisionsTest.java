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

    @Test
    @DisplayName("Testing empty dump")
    public void testEmptyDump(){
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

    
    @Test
    @DisplayName("Testing insert one value, dependent on dump()")
    public void testInsertIntoEmptyTable1(){
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

    @Test
    @DisplayName("Testing insert 2 values that collide, dependent on dump()")
    public void testInsertIntoEmptyTable2(){
        StrHashTableCollisions hashTable = new StrHashTableCollisions();

        hashTable.insert("1", "one");
        hashTable.insert("11", "eleven");
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
                        "9: 1, one\r\n" + 
                        "9: 11, eleven";
        Assertions.assertEquals(expected, actual);
    }

    




}
