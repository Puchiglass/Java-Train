package com.example;

public class Node {
    @Override
    public String toString() {
        return "(" + value + ":" + key + ")";
    }

    private final Integer key;
    private Integer value;
    private Node next;

    public Node(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

}
