package com.linkedListsCap2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestCap2 {
    public static void main(String args[]){
        System.out.println("Remove Duplicates");
        Node n = getLinkedListNode(8);
        n.printNode();
        System.out.println();
        removeDuplicates(n);
        n.printNode();
        System.out.println("******************************************\n\n");

        System.out.println("Find last node data");
        Node n2 = getLinkedListNode(10);
        n2.printNode();
        System.out.println();
        printLastNodeData(n2,10);

        System.out.println("******************************************\n\n");
        System.out.println("Delete Middle Node");
        n2.printNode();
        System.out.println(deleteMiddleNode(getMiddleNode(n2)));
        n2.printNode();
        System.out.println("******************************************\n\n");
    }

    private static Node getMiddleNode(Node n) {
        if(n == null || n.next == null ){
            return null;
        }
        return n.next;
    }

    private static boolean deleteMiddleNode(Node n) {
        if(n == null || n.next == null ){
            return false;
        }
        Node nextNode = n.next;
        n.data = nextNode.data;
        n.next = nextNode.next;
        return true;
    }


    private static int printLastNodeData(Node n2, int i) {
        if(n2 == null){
            return 0;
        }

        int index = printLastNodeData(n2.next,i) + 1;
        if(index == i){
            System.out.println(i + "th to last node is" + n2.data);
        }
        return index;
    }

    private static void removeDuplicates(Node n) {
        Set<Integer> values = new HashSet<>();
        Node previousNode = null;
        while(n != null){
            if(values.contains(n.data)){
                previousNode.next = n.next;
            }
            else{
                values.add(n.data);
                previousNode = n;
            }
            n= n.next;
        }

    }

    private static Node getLinkedListNode(int numberOfNodes){

        Node node = null;
        for(int i=0; i<numberOfNodes; i++){
            if(node == null){
                node = new Node(new Random().nextInt(5)+1);
            }
            else{
                node.appendToTail(new Random().nextInt(5)+1);
            }
        }

        return node;
    }



    static class Node{
        int data;
        Node next = null;

        Node(int d){
            data = d;
        }

        void appendToTail(int d){
            Node end = new Node(d);
            Node n = this;
            while(n.next != null){
                n = n.next;
            }
            n.next = end;
        }

        void printNode(){
            Node n = this;
            while(n != null){
                System.out.print(n.data + "\t");
                n = n.next;
            }

        }
    }
}
