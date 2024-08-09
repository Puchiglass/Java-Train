package com.example;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap implements MyMap {

    private Node[] buckets;

    private final int INITIAL_SIZE = 10;

    private final int MAX_BUCKET_SIZE = 5;

    public MyHashMap() {
        buckets = new Node[INITIAL_SIZE];
    }

    public MyHashMap(int size) {
        buckets = new Node[size];
    }

    @Override
    public void put(Integer key, Integer value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }

        int index = getIndex(key, buckets.length);
        int countNode = 1;
        Node currentNode = buckets[index];
        if (currentNode == null) {
            buckets[index] = new Node(key, value);
            return;
        }
        if (currentNode.getKey().equals(key)) {
            currentNode.setValue(value);
            return;
        }
        while (currentNode.hasNext()) {
            countNode++;
            if (currentNode.getKey().equals(key)) {
                currentNode.setValue(value);
                return;
            }
            currentNode = currentNode.getNext();
        }

        if (countNode < MAX_BUCKET_SIZE) {
            if (currentNode.getKey().equals(key)) {
                currentNode.setValue(value);
                return;
            }
            currentNode.setNext(new Node(key, value));
            return;
        }

        recharge((int) (buckets.length * 1.75));
        this.put(key, value);
    }

    @Override
    public Integer get(Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int index = getIndex(key, buckets.length);
        Node currentNode = buckets[index];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public void delete(Integer key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int index = getIndex(key, buckets.length);
        Node currentNode = buckets[index];
        if (currentNode == null) {
            return;
        }
        if (currentNode.getKey().equals(key)) {
            buckets[index] = currentNode.getNext();
            return;
        }
        while (currentNode.hasNext()) {
            Node nextNode = currentNode.getNext();
            if (nextNode.getKey().equals(key)) {
                currentNode.setNext(nextNode.getNext());
                return;
            }
            currentNode = nextNode;
        }
    }

    private int getIndex(int key, int length) {
        return Math.abs(key % length);
    }

    private void recharge(int newLength) {
        Node[] newBuckets = new Node[newLength];
        for (Node node : buckets) {
            if (node == null) {
                continue;
            }
            boolean isAdded = addNode(node, newBuckets);
            if (!isAdded) {
                recharge((int) (newLength * 1.75));
                return;
            }
            while(node.hasNext()) {
                node = node.getNext();
                isAdded = addNode(node, newBuckets);
                if (!isAdded) {
                    recharge((int) (newLength * 1.75));
                    return;
                }
            }
        }
        buckets = newBuckets;
    }

    private boolean addNode(Node newNode, Node[] buckets) {
        Node node = new Node(newNode.getKey(), newNode.getValue());
        int index = getIndex(node.getKey(), buckets.length);
        Node currentNode = buckets[index];
        if (currentNode == null) {
            buckets[index] = node;
            return true;
        }
        int countNode = 1;
        while (currentNode.hasNext()) {
            currentNode = currentNode.getNext();
            countNode++;
        }
        if (countNode >= MAX_BUCKET_SIZE) {
            return false;
        }
        currentNode.setNext(node);
        return true;
    }

    public void stat() {
        System.out.println("Size map: " + buckets.length);
        Map<Integer, Integer> bucketSize = new HashMap<>();
        bucketSize.put(0, 0);
        bucketSize.put(1, 0);
        bucketSize.put(2, 0);
        bucketSize.put(3, 0);
        bucketSize.put(4, 0);
        bucketSize.put(5, 0);
        for (int i = 0; i < buckets.length; i++) {
            Node node = buckets[i];
            if (node == null) {
                bucketSize.put(0, bucketSize.get(0) + 1);
                continue;
            }
            int count = 1;
            while (node.hasNext()) {
                count++;
                node = node.getNext();
            }
            bucketSize.put(count, bucketSize.get(count) + 1);
            if (count > MAX_BUCKET_SIZE) {
                System.out.println("Bucket " + i + " has " + count + " elements");
            }
        }
        System.out.println("Distribution by buckets filling: " + bucketSize);
    }
}
