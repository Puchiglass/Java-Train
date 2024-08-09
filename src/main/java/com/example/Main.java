package com.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Node[] nodes = new Node[3];
        nodes[0] = new Node(1,1);
        nodes[1] = new Node(2,2);
        System.out.println(Arrays.toString(nodes));

        nodes[0] = null;
        System.out.println(Arrays.toString(nodes));
    }
}
