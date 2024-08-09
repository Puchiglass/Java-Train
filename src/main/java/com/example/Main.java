package com.example;

public class Main {

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(-100, 100);
        map.put(1, 1);
        map.put(2, 2);
        map.put(2342423, 2342423);

        map.put(1, 100);
        map.put(2, 200);
        map.delete(1);

        map.stat();
        System.out.println(map);
    }
}
