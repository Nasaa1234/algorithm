package lab.tests;

import lab.functions.HashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    private HashTable hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable(10);
        hashTable.put("A101", "Book");
        hashTable.put("A102", "Pen");
        hashTable.put("A103", "Paper");
    }

    @Test
    void testPutAndGet() {
        assertEquals("Book", hashTable.get("A101"));
        assertEquals("Pen", hashTable.get("A102"));
        assertEquals("Paper", hashTable.get("A103"));
    }

    @Test
    void testUpdateElement() {
        Object oldValue = hashTable.updateElement("A101", "Notebook");
        assertEquals("Book", oldValue);
        assertEquals("Notebook", hashTable.get("A101"));
    }

    @Test
    void testUpdateElement_KeyNotFound() {
        Object result = hashTable.updateElement("A999", "Unknown");
        assertNull(result);
    }

    @Test
    void testUpdateKey() {
        Object value = hashTable.updateKey("A102", "A202");
        assertEquals("Pen", value);
        assertNull(hashTable.get("A102"));
        assertEquals("Pen", hashTable.get("A202"));
    }
    
    @Test
    void testDelete() {
        hashTable.delete("A103");
        assertNull(hashTable.get("A103"));
    }

}
