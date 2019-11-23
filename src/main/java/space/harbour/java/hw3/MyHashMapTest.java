package main.java.space.harbour.java.hw3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MyHashMapTest {

    private MyHashMap<String, String> myMap;
    private final int number3 = 3;
    private final int number1000 = 1000;

    public MyHashMapTest() {
    }

    @Before
    public void setUp() {
        myMap = new MyHashMap<String, String>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNewMapIsEmpty() {
        assertTrue(myMap.isEmpty());
    }

    @Test
    public void testPairInsertion() {
        myMap.put("java", "1");
        myMap.put("javascript", "11");
        assertEquals(2, myMap.size());
    };


    @Test
    public void testCheckForExistingPair() {
        myMap.put("java", "1");
        assertTrue(myMap.containsKey("java"));
        assertTrue(myMap.containsValue("1"));
    }

    @Test
    public void testGetPair() {
        myMap.put("hello", "10");
        myMap.put("world", "15");
        assertEquals("10", myMap.get("hello"));
    }

    @Test
    public void testGetNonExistingPair() {
        myMap.put("Java", "11");
        assertFalse(myMap.containsKey("java"));
        assertTrue(myMap.containsValue("11"));
        assertNull(myMap.get("java"));
    }

    @Test
    public void testMapSizeAfterAddingNewElement() {
        myMap.put("abc", "1");
        myMap.put("def", "2");
        assertEquals(2, myMap.size());
    }

    @Test
    public void testRemoveOneElement() {
        myMap.put("Java", "11");
        myMap.put("Is", "10");
        myMap.put("Fun", "15");

        assertEquals(number3, myMap.size());

        myMap.remove("Is");

        assertEquals(2, myMap.size());
    }

    @Test
    public void testRemoveAllKeys() {
        myMap.put("one", "1");
        myMap.put("two", "2");
        myMap.put("three", "3");
        myMap.put("hello", "4");
        myMap.put("five", "5");

        myMap.clear();
        assertEquals(0, myMap.size());
    }

    @Test
    public void testInsertPairsWithSameKey() {
        myMap.put("java", "11");
        myMap.put("java", "99");

        assertEquals(2, myMap.size());
    }

    @Test
    public void testInsertNullValue() {
        myMap.put("r", null);
        assertEquals(1, myMap.size());
    }

    @Test
    public void testInsertSameValue() {
        myMap.put("r", "1");
        myMap.put("s", "1");
        assertTrue(myMap.containsValue("1"));
        assertEquals("1", myMap.get("r"));
        assertEquals("1", myMap.get("r"));
    }

    @Test
    public void testGetValueFromPairsWithSameKeys() {
        myMap.put("Hello", "99");
        myMap.put("Hello", "15");

        assertEquals("99", myMap.get("Hello"));
    }

    @Test
    public void testIsEmptyAfterInsertion() {
        myMap.put("Hello", "99");
        assertFalse(myMap.isEmpty());
    }

    @Test
    public void testGetAllValues() {
        myMap.put("Hello", "1");
        myMap.put("WOrld", "2");
        myMap.put("Java", "99");
        myMap.put("Programming is fun", "25");
        assertNotNull(myMap.values());
    }

    @Test
    public void testRemoveElementWithSameKey() {
        myMap.put("Hello", "1");
        myMap.put("Hello", "2");
        assertEquals(2, myMap.size());
        myMap.remove("Hello");
        assertFalse(myMap.isEmpty());
        assertEquals(1, myMap.size());
    }

    @Test
    public void testRemoveFromEmptyMap() {
        assertNull(myMap.remove("a"));
    }

    @Test
    public void testInsertPairsWithCollision() {
        myMap.put("name", "1");
        myMap.put("name2", "2");

        assertNotNull(myMap.get("name2"));
        assertEquals("2", myMap.get("name2"));
    }

    @Test
    public void testIsEmptyWithSameKey() {
        myMap.put("String 1", "1");
        myMap.put("String 1", "2");
        assertFalse(myMap.isEmpty());
    }

    @Test
    public void testRemoveWithHashCollisions() {
        myMap.put("FB", "7");
        myMap.put("Ea", "8");
        assertNotNull(myMap.get("Ea"));
        assertNotNull(myMap.get("FB"));
        assertEquals("8", myMap.get("Ea"));
        assertEquals("7", myMap.get("FB"));
    }

    @Test
    public void testReturnsCorrectValue() {
        myMap.put("Hello World!", "Coding101");
        assertEquals("Coding101", myMap.get("Hello World!"));
    }

    @Test
    public void testInsertThousandKeys() {
        for (int i = 0; i < number1000; i++) {
            myMap.put(String.valueOf(i), String.valueOf(i + i));
        }
        assertEquals(number1000, myMap.size());
        for (int i = 0; i < number1000; i++) {
            assertEquals(String.valueOf(i + i), myMap.get(String.valueOf(i)));
        }
    }
}
