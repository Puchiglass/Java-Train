package com.example;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void test() {
        MyHashMap map = new MyHashMap();
        map.put(-2, null);
        map.put(-1, -1);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);

        assertNull(map.get(-2));
        assertEquals(-1, map.get(-1));
        assertEquals(0, map.get(0));
        assertEquals(1, map.get(1));
        assertEquals(2, map.get(2));

        map.delete(1);
        assertNull(map.get(1));

        map.put(2, 20);
        assertEquals(20, map.get(2));
    }

    @Test
    void testInitSize() {
        MyHashMap map = new MyHashMap(5);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);

        System.out.println("Test init size");
        map.stat();
        System.out.println("\n");
    }

    @Test
    void testNullKey() {
        MyHashMap map = new MyHashMap();
        assertThrows(IllegalArgumentException.class, () -> map.put(null, 1));
        assertThrows(IllegalArgumentException.class, () -> map.get(null));
        assertThrows(IllegalArgumentException.class, () -> map.delete(null));
    }

    @Test
    void testManyElements() {
        MyHashMap map = new MyHashMap();
        for (int i = 0; i < 15_623_479; i++) {
            map.put(i, i);
        }
        for (int i = 0; i < 15_623_479; i++) {
            assertEquals(i, map.get(i));
        }

        System.out.println("Test many elements");
        map.stat();
        System.out.println("\n");
    }

    @Test
    void testRandom() {
        MyHashMap map = new MyHashMap();
        Random random = new Random();
        for (int i = 0; i < 100_000; i++) {
            map.put(random.nextInt(20001) - 10000,
                    random.nextInt(20001) - 10000);
        }

        System.out.println("Test random");
        map.stat();
        System.out.println("\n");
    }

}